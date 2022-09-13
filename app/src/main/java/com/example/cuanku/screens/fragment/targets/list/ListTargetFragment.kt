package com.example.cuanku.screens.fragment.targets.list

import android.content.Intent
import android.net.Network
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentListTargetBinding
import com.example.cuanku.databinding.FragmentTargetsBinding
import com.example.cuanku.response.DataListTargets
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.example.cuanku.screens.activity.targets.detail.DetailTargetActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListTargetFragment : BaseFragment<FragmentListTargetBinding>(),
    ListTargetAdapter.OnItemClickListener, ListTargetAdapter.OnDeleteListener {

    private val viewModel: TargetsViewModel by viewModels()
    private lateinit var listTargetAdapter: ListTargetAdapter

    override fun setLayout(inflater: LayoutInflater): FragmentListTargetBinding {
        return FragmentListTargetBinding.inflate(inflater)
    }

    override fun initialization() {
        setupListTarget()
        swiperefreshLayout()
    }

    override fun observeViewModel() {
        getData()
        viewModel.deleteTarget.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        this.dismissLoading()
                        getData()
                    }
                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
                else -> {}
            }
        }

    }

    private fun getData() {
        viewModel.getListTargets()
        viewModel.listTargets.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
                        this.dismissLoading()
                        listTargetAdapter.differ.submitList(data)
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

    private fun swiperefreshLayout() {
        binding.layoutRecyclerView.swipeRefresh.setOnRefreshListener {
            observeViewModel()
            listTargetAdapter.differ.currentList.size
            binding.layoutRecyclerView.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupListTarget() {
        listTargetAdapter = ListTargetAdapter(this@ListTargetFragment, this@ListTargetFragment)
        binding.layoutRecyclerView.recyclerView.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            linearLayout.reverseLayout = true
            linearLayout.stackFromEnd = true
            layoutManager = linearLayout
            adapter = listTargetAdapter
            visibility = VISIBLE
        }
    }

    override fun onItemClicked(data: DataListTargets) {
        startActivity(
            Intent(context, DetailTargetActivity::class.java)
                .putExtra("TARGET", data)
        )
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDeleteClicked(data: DataListTargets) {
        viewModel.deleteTarget(data.id)
        Log.e("DELETEID", "onDeleteClicked: ${data.id}")
    }

}