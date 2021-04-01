package com.koba.kobatodo.presenter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.koba.kobatodo.base.BaseViewBindingFragment
import com.koba.kobatodo.databinding.FragmentTodoBinding

class TodoFragment : BaseViewBindingFragment<FragmentTodoBinding>() {

    companion object {
        fun newInstance() = TodoFragment()
    }

    private val viewModel: TodoViewModel by viewModels()

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?)
            = FragmentTodoBinding.inflate(inflater, container, false)

    override fun onSetupViews(view: View) {
        super.onSetupViews(view)
    }

    override fun onBindViewModels() {
        super.onBindViewModels()
    }
}