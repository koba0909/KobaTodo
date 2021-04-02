package com.koba.kobatodo.domain.model

data class TodoModel(
    val id: Int,
    var description: String,
    var state: TodoState,
    val date: String
) {
    override fun toString(): String =
        "id : $id\n" +
                "description : $description\n" +
                "state : ${state.javaClass.simpleName}\n" +
                "date : $date"
}

sealed class TodoState {
    object Complete : TodoState()
    object UnChecking : TodoState()
    object Unknown : TodoState()
}

fun TodoState.toggle(): TodoState {
    return when (this) {
        is TodoState.Complete -> TodoState.UnChecking
        is TodoState.UnChecking -> TodoState.Complete
        else -> TodoState.Unknown
    }
}