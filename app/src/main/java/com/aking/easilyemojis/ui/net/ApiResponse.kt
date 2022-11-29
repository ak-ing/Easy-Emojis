package com.aking.easilyemojis.ui.net

import java.io.Serializable

open class ApiResponse<T>(
    open val data: T? = null,
    open val code: Int? = null,
    open val msg: String? = null,
    open val status: Int? = null,
    open val error: Throwable? = null,
) : Serializable {
    val isSuccess: Boolean
        get() = status == 0

    override fun toString(): String {
        return "ApiResponse(data=$data, errorCode=$code, errorMsg=$msg, error=$error)"
    }

}

class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(data = response)

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiFailedResponse<T>(override val code: Int?, override val msg: String?) :
    ApiResponse<T>(code = code, msg = msg)

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>(error = throwable)
