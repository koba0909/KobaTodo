package com.koba.kobatodo.data.service

import com.koba.kobatodo.data.dto.TodoResponse
import io.reactivex.Single
import retrofit2.http.POST

interface TodoService {
    @POST("list")
    fun getTodoList(): Single<TodoResponse>
}