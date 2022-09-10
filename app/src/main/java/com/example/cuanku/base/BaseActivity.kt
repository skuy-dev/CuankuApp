package com.example.cuanku.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.cuanku.utils.LoadingDialog

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), BaseView {

    protected lateinit var binding: VB

    protected abstract fun setLayout(inflater: LayoutInflater): VB

    private var loading: LoadingDialog? = null

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

    override fun showLoading() {
        if (loading == null)
            loading = LoadingDialog()

        loading?.show(supportFragmentManager, "LOADING_DIALOG")

    }

    override fun dismissLoading() {
        loading?.dismissAllowingStateLoss()
    }

}