package com.example.cuanku.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.cuanku.utils.LoadingDialog

abstract class BaseFragment<VB : ViewBinding> : Fragment(), BaseView {

    protected lateinit var binding: VB
    protected abstract fun setLayout(inflater: LayoutInflater): VB

    private var loading: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getInflatedLayout(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        observeViewModel()
    }

    abstract fun initialization()

    abstract fun observeViewModel()

    private fun getInflatedLayout(layoutInflater: LayoutInflater): View {
        binding = setLayout(layoutInflater)
        return binding.root
    }


    override fun showLoading() {
        if (loading == null)
            loading = LoadingDialog()

        loading?.show(parentFragmentManager, "LOADING_DIALOG")

    }

    override fun dismissLoading() {
        loading?.dismissAllowingStateLoss()
    }

}