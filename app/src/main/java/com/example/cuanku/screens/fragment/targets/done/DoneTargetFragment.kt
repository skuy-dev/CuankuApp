package com.example.cuanku.screens.fragment.targets.done

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentDoneTargetBinding
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.example.cuanku.screens.fragment.targets.list.ListTargetAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoneTargetFragment : BaseFragment<FragmentDoneTargetBinding>() {


    private val viewModel: TargetsViewModel by viewModels()
    private lateinit var donetargetAdapter: DoneTargetAdapter

    override fun setLayout(inflater: LayoutInflater): FragmentDoneTargetBinding {
        return FragmentDoneTargetBinding.inflate(inflater)
    }

    override fun initialization() {
//        swiperefreshLayout()
        setupListTarget()
    }

    override fun observeViewModel() {
        viewModel.getListdoneTarget()
        viewModel.listdoneTarget.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
                        this.dismissLoading()
                        donetargetAdapter.differ.submitList(data)
                        binding.layoutRecyclerView.root.isVisible = true
                    } else {
                        binding.layoutEmpty.root.isVisible = true
                    }
                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
                else -> {

                }
            }
        }
    }

//    private fun swiperefreshLayout() {
//        binding.layoutRecyclerView.swipeRefresh.setOnRefreshListener {
//            observeViewModel()
//            donetargetAdapter.differ.currentList.size
//            binding.layoutRecyclerView.swipeRefresh.isRefreshing = false
//        }
//    }

    private fun setupListTarget() {
        donetargetAdapter = DoneTargetAdapter()
        binding.layoutRecyclerView.recyclerView.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            linearLayout.reverseLayout = true
            linearLayout.stackFromEnd = true
            layoutManager = linearLayout
            adapter = donetargetAdapter
            visibility = View.VISIBLE
        }
    }

}