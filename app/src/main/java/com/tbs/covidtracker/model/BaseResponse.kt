package com.tbs.covidtracker.model

open class BaseResponse(
    val isSuccess: Boolean = true,
    val error: Throwable? = null,
    val errorString: String? = null
)
