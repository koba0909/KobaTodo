package com.koba.kobatodo.data.repository

import com.koba.kobatodo.data.dto.TodoResponse
import io.reactivex.Single

interface TodoRepository {
    fun getTodoList(): Single<TodoResponse>
}