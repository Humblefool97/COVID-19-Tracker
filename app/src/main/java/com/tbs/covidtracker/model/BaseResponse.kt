package com.tbs.covidtracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseResponse(
    val isSuccess: Boolean = true,
    val error: Throwable? = null,
    val errorString: String? = null
):Parcelable
