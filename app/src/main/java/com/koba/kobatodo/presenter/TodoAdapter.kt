package com.koba.kobatodo.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koba.kobatodo.databinding.ItemTodoListBinding
import com.koba.kobatodo.domain.TodoModel

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private val _todoList = arrayListOf<TodoModel>()
    val todoList: List<TodoModel>
        get() = _todoList

    override fun getItemCount() = _todoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class TodoViewHolder(binding: ItemTodoListBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}