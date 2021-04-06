package com.koba.kobatodo.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koba.kobatodo.databinding.ItemTodoListBinding
import com.koba.kobatodo.domain.model.TodoModel
import com.koba.kobatodo.domain.model.TodoState
import com.koba.kobatodo.domain.usecase.TodoUseCase
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

    private val _clickSubject = PublishSubject.create<Triple<View, Int, TodoModel>>()
    val clickSubject: Subject<Triple<View, Int, TodoModel>>
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

    inner class TodoViewHolder(
        private val binding: ItemTodoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding) {
                root.setOnClickListener {
                    _clickSubject.onNext(Triple(it, adapterPosition, _todoList[adapterPosition]))
                }
                checkBoxState.setOnClickListener {
                    _clickSubject.onNext(Triple(it, adapterPosition, _todoList[adapterPosition]))
                }
            }
        }

        fun bind(item: TodoModel) {
            with(binding) {
                tvId.text = item.id.toString()
                tvDescription.text = item.description
                when (item.state) {
                    is TodoState.Complete -> {
                        checkBoxState.isChecked = true
                        checkBoxState.text = TodoUseCase.STATUS_COMPLETE
                    }
                    is TodoState.UnChecking -> {
                        checkBoxState.isChecked = false
                        checkBoxState.text = TodoUseCase.STATUS_UN_CHECKING
                    }
                    is TodoState.Unknown -> {
                        checkBoxState.isChecked = false
                        checkBoxState.text = ""
                    }
                }
                tvDate.text = item.date
            }
        }
    }
}