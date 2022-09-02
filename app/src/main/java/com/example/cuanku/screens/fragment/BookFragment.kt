package com.example.cuanku.screens.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.cuanku.R
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentBookBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.screens.fragment.targets.TargetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding>() {


    @Inject
    lateinit var userSession: AppManager

    override fun setLayout(inflater: LayoutInflater): FragmentBookBinding {
        return FragmentBookBinding.inflate(inflater)
    }

    override fun initialization() {
        setupView()
    }

    override fun observeViewModel() {

    }

    private fun setupView() {
        binding.txtName.text = userSession.getName(AppManager.PREFS_TOKEN)
    }


}