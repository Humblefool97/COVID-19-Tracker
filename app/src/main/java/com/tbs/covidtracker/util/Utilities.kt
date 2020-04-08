package com.tbs.covidtracker.util

import android.icu.text.NumberFormat
import android.os.Build
import timber.log.Timber
import java.lang.NumberFormatException

class Utilities {
    companion object {
        fun getFormattedNumber(numberTobeFormatted: String): String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val numberFormat = NumberFormat.getNumberInstance()
                try {
                    val longValue = numberTobeFormatted.toLong()
                    return numberFormat.format(longValue)
                } catch (numberFormatException: NumberFormatException) {
                    Timber.tag("LOCAL").e(numberFormatException)
                }

            }
            return numberTobeFormatted

        }
    }
}