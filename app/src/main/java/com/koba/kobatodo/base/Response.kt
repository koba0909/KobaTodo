package com.koba.kobatodo.base

open class Response<T>(
    val code: Int? = null,
    val data: T? = null,
    val message: String? = null
)