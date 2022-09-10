package com.example.cuanku.screens.fragment.targets

import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentTargetsBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.response.DataListTargets
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.example.cuanku.screens.activity.targets.detail.DetailTargetActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TargetsFragment : BaseFragment<FragmentTargetsBinding>(), TargetsAdapter.OnItemClickListener {

    private val viewModel: TargetsViewModel by viewModels()
    private lateinit var targetsAdapter: TargetsAdapter

    @Inject
    lateinit var userSession: AppManager

    override fun setLayout(inflater: LayoutInflater): FragmentTargetsBinding {
        return FragmentTargetsBinding.inflate(inflater)
    }

    override fun initialization() {
        setupListTarget()
        swiperefreshLayout()
    }

    override fun observeViewModel() {
        viewModel.getListTargets()
        viewModel.listTargets.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.isNullOrEmpty()) {
                        targetsAdapter.differ.submitList(data)

                    }
                }
                is NetworkResult.Loading -> {
                    binding.shimmerListTarget.root.startShimmerAnimation()
                }
            }
        }
    }

    private fun swiperefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener {
            observeViewModel()
            targetsAdapter.differ.currentList.size
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupListTarget() {
        targetsAdapter = TargetsAdapter(this@TargetsFragment)
        binding.shimmerListTarget.root.visibility = GONE
        binding.rvListTargets.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            linearLayout.reverseLayout = true
            linearLayout.stackFromEnd = true
            layoutManager = linearLayout
            adapter = targetsAdapter
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
        swiperefreshLayout()
    }


}