package com.lupi.magicapp.utils

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val errorType: ErrorType? = null
) {
    class Success<T>(data: T?) : ApiResponse<T>(data)
    class Error<T>(errorType: ErrorType?, message: String? = null, data: T? = null) : ApiResponse<T>(data, message, errorType)
    class Loading<T> : ApiResponse<T>()
}