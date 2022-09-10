package com.example.cuanku.screens.activity.targets.detail

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityDetailTargetBinding
import com.example.cuanku.helper.Constants.PATH_IMAGE
import com.example.cuanku.helper.convertToRupiah
import com.example.cuanku.response.DataItem
import com.example.cuanku.response.DataListTargets
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus


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
        setOnClickListener()
        swiperefreshLayout()
    }

    private fun getData() {
        target = intent.getParcelableExtra("TARGET")
    }

    override fun observeViewModel() {
        viewModel.getDetailTarget(target?.id)
        viewModel.detailTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    this.dismissLoading()
                    binding.root.visibility = VISIBLE
                    val data = response.data?.data
                    setupView(data)
                    onRestart()
                    swiperefreshLayout()
                    val dataHistory = response.data?.data?.userTargets
                    if (!dataHistory.isNullOrEmpty()) {
                        Log.e("IMAGE", "observeViewModel: $data")
//                        detailTargetAdapter.differ.submitList(dataHistory)
                        detailTargetAdapter.setData(dataHistory)

                    }

                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
            }
        }
    }

    private fun swiperefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener {
            observeViewModel()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupView(data: DataItem?) {
        binding.layoutTarget.apply {

            txtNamaTarget.text = data?.name
            txtDuration.text = "Target Tercapai ${data?.duration}"
            txtNominal.text = convertToRupiah(data?.nominal?.toDouble())
            txtCountdown.text = "${data?.duration} Bulan Lagi !"
            imgTarget.load(PATH_IMAGE + data?.imageUrl)

            val kakumpul = data?.remaining?.let { data.nominal?.minus(it) }
            txtTerkumpul.text = convertToRupiah(kakumpul?.toDouble())

            val percentage = (kakumpul!!.toDouble() / data.nominal!! * 100).toInt()
            txtPersentase.text = "$percentage%"
            progressBar.progress = percentage

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

    private fun setOnClickListener() {
        binding.btnAddTabunganTarget.setOnClickListener {
            val dialog = TabungantargetDialog(target?.id)
            dialog.show(supportFragmentManager, "TABUNGANTARGET")
            swiperefreshLayout()
        }
    }

    override fun onRestart() {
        super.onRestart()
        setOnClickListener()
    }

}