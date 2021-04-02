package com.koba.kobatodo.domain.usecase

import com.koba.kobatodo.data.repository.TodoRepository
import com.koba.kobatodo.domain.model.TodoModel
import com.koba.kobatodo.domain.model.TodoState
import io.reactivex.schedulers.Schedulers

class TodoUseCase(private val todoRepository: TodoRepository) {
    fun getTodoList() =
        todoRepository.getTodoList()
            .subscribeOn(Schedulers.io())
            .map {
                if (it.code != NETWORK_SUCCESS) {
                    throw Exception("Network error")
                } else {
                    it.data?.map { todoDto ->
                        TodoModel(
                            id = todoDto.id,
                            description = todoDto.description ?: "",
                            date = todoDto.date ?: "",
                            state = when (todoDto.status) {
                                STATUS_COMPLETE -> TodoState.Complete
                                STATUS_UN_CHECKING -> TodoState.UnChecking
                                else -> TodoState.Unknown
                            }
                        )
                    } ?: throw Exception("Todo list is empty")
                }
            }

    companion object {
        const val NETWORK_SUCCESS = 1
        const val STATUS_COMPLETE = "complete"
        const val STATUS_UN_CHECKING = "todo"
    }
}