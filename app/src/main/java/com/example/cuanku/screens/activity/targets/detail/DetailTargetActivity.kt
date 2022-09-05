package com.example.cuanku.screens.activity.targets.detail

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityDetailTargetBinding
import com.example.cuanku.helper.Constants.PATH_IMAGE
import com.example.cuanku.response.DataItem
import com.example.cuanku.response.DataListTargets
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTargetActivity : BaseActivity<ActivityDetailTargetBinding>() {

    private val viewModel: TargetsViewModel by viewModels()

    private lateinit var detailTargetAdapter: DetailTargetAdapter

    var target: DataListTargets? = null

    override fun setLayout(inflater: LayoutInflater): ActivityDetailTargetBinding {
        return ActivityDetailTargetBinding.inflate(inflater)
    }

    override fun initialization() {
        getData()
        setupHistoryTarget()
    }

    private fun getData() {
        target = intent.getParcelableExtra("TARGET")
    }

    override fun observeViewModel() {
        viewModel.getDetailTarget(target?.id)
        viewModel.detailTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    setupView(data)
                    val dataHistory = response.data?.data?.userTargets
                    if (!dataHistory.isNullOrEmpty()) {
                        detailTargetAdapter.differ.submitList(dataHistory)
                    }

                }
            }
        }
    }

    private fun setupView(data: DataItem?) {
        binding.run {
            txtNameTarget.text = data?.name
            txtPerkiraan.text = data?.duration
            imgTarget.load(PATH_IMAGE + data?.imageUrl)
        }
    }

    private fun setupHistoryTarget() {
        detailTargetAdapter = DetailTargetAdapter()
        binding.rvHistoryTarget.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            linearLayout.reverseLayout = true
            linearLayout.stackFromEnd = true
            layoutManager = linearLayout
            adapter = detailTargetAdapter
        }
    }


}