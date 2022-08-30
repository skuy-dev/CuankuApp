package com.example.cuanku.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract fun setLayout(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout())

        initialization()
        observeViewModel()

    }

    abstract fun initialization()

    abstract fun observeViewModel()

    private fun getInflatedLayout(): View {
        binding = setLayout(layoutInflater)
        return binding.root
    }

}