package com.koba.kobatodo.presenter

import android.database.Observable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koba.kobatodo.databinding.ItemTodoListBinding
import com.koba.kobatodo.domain.TodoModel
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private val _todoList = arrayListOf<TodoModel>()
    var todoList: List<TodoModel>
        get() = _todoList
        set(value) {
            _todoList.clear()
            _todoList.addAll(value)
            notifyDataSetChanged()
        }

    private val _clickSubject = PublishSubject.create<Pair<View, TodoModel>>()
    val clickSubject: Subject<Pair<View, TodoModel>>
        get() = _clickSubject

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
        holder.bind(_todoList[position])
    }

    inner class TodoViewHolder(binding: ItemTodoListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                _clickSubject.onNext(it to _todoList[adapterPosition])
            }
        }

        fun bind(item: TodoModel){

        }
    }
}