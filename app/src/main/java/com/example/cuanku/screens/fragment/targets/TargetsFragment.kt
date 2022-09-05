package com.example.cuanku.screens.fragment.targets

import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuanku.R
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentTargetsBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.response.DataListTargets
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TargetsFragment : BaseFragment<FragmentTargetsBinding>() {

    private val viewModel: TargetsViewModel by viewModels()
    private lateinit var targetsAdapter: TargetsAdapter

    @Inject
    lateinit var userSession: AppManager

    override fun setLayout(inflater: LayoutInflater): FragmentTargetsBinding {
        return FragmentTargetsBinding.inflate(inflater)
    }

    override fun initialization() {
        setonClickListener()
        setupListTarget()
    }

    override fun observeViewModel() {
        viewModel.getListTargets()
        viewModel.listTargets.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
//                        targetsAdapter.differ.submitList(data)
                        targetsAdapter.setData(data)
                    }
                }
            }
        }
    }

    private fun setupListTarget() {
        targetsAdapter = TargetsAdapter()
        binding.rvListTargets.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            linearLayout.reverseLayout = true
            linearLayout.stackFromEnd = true
            layoutManager = linearLayout
            adapter = targetsAdapter
        }
    }


    private fun setonClickListener() {
        binding.btnAddTarget.setOnClickListener {
            val dialog = AddTargetsFragment()
            dialog.show(parentFragmentManager, "dialogaddtarget")
        }
    }

}