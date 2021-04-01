package com.koba.kobatodo.domain

data class TodoModel (
    val title: String,
    val description: String,
    val state: TodoState
)

sealed class TodoState {
    object Complete : TodoState()
    object Checking : TodoState()
}