package com.hamzawysat.app.core.network
sealed interface ApiResult<out T>{data class Success<T>(val value:T):ApiResult<T>;data class Failure(val message:String,val cause:Throwable?=null):ApiResult<Nothing>;data object Loading:ApiResult<Nothing>}
