package com.koba.kobatodo.data.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create
import retrofit2.converter.gson.GsonConverterFactory

object TodoServiceProvider {
    private const val BASE_URL = "http://localhost/"

    val todoService: TodoService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(create())
        .build()
        .create(TodoService::class.java)
}