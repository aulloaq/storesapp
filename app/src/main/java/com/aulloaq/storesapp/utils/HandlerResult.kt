package com.aulloaq.storesapp.utils

sealed class HandlerResult<out T> {
    data class Success<out T>(val value: T) : HandlerResult<T>()
    data class Failure(val exception: Exception) : HandlerResult<Nothing>()
}