package com.koba.kobatodo.data.repository

import com.koba.kobatodo.data.dto.TodoResponse
import com.koba.kobatodo.data.service.TodoService
import io.reactivex.Single

class TodoRepositoryImpl(
    private val todoService: TodoService
) : TodoRepository {
    override fun getTodoList(): Single<TodoResponse> =
        todoService.getTodoList()
}