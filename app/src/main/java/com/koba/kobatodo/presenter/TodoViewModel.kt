package com.koba.kobatodo.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.koba.kobatodo.base.BaseViewModel
import com.koba.kobatodo.domain.model.TodoModel
import com.koba.kobatodo.domain.usecase.TodoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class TodoViewModel(
    private val todoUseCase: TodoUseCase
) : BaseViewModel() {

    private val _todoListLiveData = MutableLiveData<List<TodoModel>>()
    val todoListLiveData: LiveData<List<TodoModel>>
        get() = _todoListLiveData

    init {
        fetchTodoList()
    }

    private fun fetchTodoList() {
        todoUseCase.getTodoList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _todoListLiveData.value = it
                },
                {
                    Log.e("hugh", "error cause : ${it.message}")
                }
            )
            .addTo(compositeDisposable)
    }
}