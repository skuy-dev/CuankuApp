package com.example.cuanku.screens.fragment.targets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuanku.R
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentTargetsBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.response.DataListTargets
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TargetsFragment : BaseFragment<FragmentTargetsBinding>() {

    private val viewModel: TargetsViewModel by viewModels()

    private var targetsAdapter = TargetsAdapter()

    @Inject
    lateinit var userSession: AppManager

    override fun setLayout(inflater: LayoutInflater): FragmentTargetsBinding {
        return FragmentTargetsBinding.inflate(inflater)
    }

    override fun initialization() {

    }

    override fun observeViewModel() {
        val token = userSession.getToken(AppManager.PREFS_TOKEN)
        viewModel.getListTargets("Bearer $token")
        viewModel.listTargets.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
                        setupListTarget(data)
                    }
                }
            }
        }
    }

    private fun setupListTarget(list: ArrayList<DataListTargets>?) {
        targetsAdapter.apply {
            setNewInstance(list?.toMutableList())
        }
        binding.rvListTargets.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = targetsAdapter
        }
    }


}