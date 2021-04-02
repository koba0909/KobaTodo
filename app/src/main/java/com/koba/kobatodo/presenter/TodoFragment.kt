package com.koba.kobatodo.presenter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.koba.kobatodo.R
import com.koba.kobatodo.base.BaseViewBindingFragment
import com.koba.kobatodo.data.repository.MockTodoRepositoryImpl
import com.koba.kobatodo.databinding.FragmentTodoBinding
import com.koba.kobatodo.domain.model.TodoModel
import com.koba.kobatodo.domain.model.toggle
import com.koba.kobatodo.domain.usecase.TodoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoFragment : BaseViewBindingFragment<FragmentTodoBinding>() {

    companion object {
        fun newInstance() = TodoFragment()
    }

    private val viewModel: TodoViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TodoViewModel(TodoUseCase(MockTodoRepositoryImpl())) as T
            }
        }
    }

    private val todoAdapter by lazy { TodoAdapter() }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentTodoBinding.inflate(inflater, container, false)

    override fun onSetupViews(view: View) {
        super.onSetupViews(view)

        binding.rvTodo.adapter = todoAdapter

        todoAdapter.clickSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onTodoClick(it.first, it.second, it.third)
            }
            .addToDisposables()
    }

    override fun onBindViewModels() {
        super.onBindViewModels()

        viewModel.todoListLiveData.observe(viewLifecycleOwner, Observer { todoList ->
            todoAdapter.todoList = todoList
        })
    }

    private fun onTodoClick(view: View, index: Int, item: TodoModel) {
        when (view.id) {
            R.id.cl_root -> Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
            R.id.checkBox_state -> if (view is CheckBox) {
                item.state = item.state.toggle()
                todoAdapter.notifyItemChanged(index)
            }
        }
    }
}