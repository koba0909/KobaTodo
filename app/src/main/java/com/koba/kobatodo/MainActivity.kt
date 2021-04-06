package com.koba.kobatodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.koba.kobatodo.presenter.TodoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment, TodoFragment.newInstance())
            .commit()
    }
}