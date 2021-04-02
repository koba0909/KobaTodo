package com.koba.kobatodo.data.repository

import com.google.gson.Gson
import com.koba.kobatodo.data.MockData
import com.koba.kobatodo.data.dto.TodoResponse
import io.reactivex.Single

class MockTodoRepositoryImpl : TodoRepository {
    override fun getTodoList() =
        Single.just(
            Gson().fromJson(
                MockData.todoList,
                TodoResponse::class.java
            )
        )
}