package com.tbs.covidtracker.ui.allcases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tbs.covidtracker.R
import com.tbs.covidtracker.state.State
import com.tbs.covidtracker.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_worldwide.*
import kotlinx.android.synthetic.main.layout_progress.*
import timber.log.Timber
import javax.inject.Inject

@Suppress("IMPLICIT_CAST_TO_ANY")
class AllCasesFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var allCasesViewModel: AllCasesViewModel
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allCasesViewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_worldwide, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allCasesViewModel.fetchAllCases().observe(this, Observer { state ->
            when (state) {
                is State.Success -> {
                    progressContainer.visibility = View.GONE

                    state.data?.let {
                        setTileData(
                            casesCardView
                            , R.drawable.vc_diagnosis
                            , getString(R.string.tile_cases)
                            , it.totalCases
                        )
                        setTileData(
                            recoveredCardView
                            , R.drawable.vc_recovery
                            , getString(R.string.tile_recovered)
                            , it.totalRecovered
                        )
                        setTileData(
                            deathsCardView
                            , R.drawable.vc_dead
                            , getString(R.string.tile_death)
                            , it.totalDeaths
                        )
                        setTileData(
                            activeCardView
                            , R.drawable.vc_active
                            , getString(R.string.tile_active)
                            , it.totalActive
                        )
                    }
                }
                else -> {
                    progressContainer.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setTileData(tile: View, icon: Int, title: String, number: String) {
        val color = getTextColor(title)
        tile.findViewById<ImageView>(R.id.dashBoardImage).setImageResource(icon)
        tile.findViewById<TextView>(R.id.tileTitleTextView).run {
            text = title
        }
        val deathTextView = tile.findViewById<TextView>(R.id.titleNumberTextView)
        deathTextView.apply {
            text = number
            if (color != -1)
                setTextColor(resources.getColor(color))
        }
    }

    private fun getTextColor(title: String): Int {
        return when (title) {
            getString(R.string.tile_cases) -> R.color.colorPrimary
            getString(R.string.tile_recovered) -> R.color.colorPrimaryDark
            getString(R.string.tile_death) -> R.color.color_danger
            getString(R.string.tile_active) -> R.color.colorGrey
            else -> {
                Timber.tag(TAG).w("Invalid title")
                -1
            }
        }
    }

    companion object {
        const val TAG = "AllCasesFragment"
        fun replaceFragment(
            allCasesFragment: AllCasesFragment,
            containerId: Int,
            fragmentManager: FragmentManager
        ) {
            fragmentManager
                .beginTransaction()
                .replace(containerId, allCasesFragment)
                .commit()
        }
    }
}