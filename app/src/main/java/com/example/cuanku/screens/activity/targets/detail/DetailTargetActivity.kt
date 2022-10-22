package com.example.cuanku.screens.activity.targets.detail

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.developer.kalert.KAlertDialog
import com.example.cuanku.R
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityDetailTargetBinding
import com.example.cuanku.helper.Constants.PATH_IMAGE
import com.example.cuanku.helper.convertToRupiah
import com.example.cuanku.response.DetailTargetItem
import com.example.cuanku.response.ListTargetItem
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailTargetActivity : BaseActivity<ActivityDetailTargetBinding>(),
    TabungantargetDialog.OnItemClickListener {

    private val viewModel: TargetsViewModel by viewModels()

    private lateinit var detailTargetAdapter: DetailTargetAdapter

    var target: ListTargetItem? = null
    var state = 1

    override fun setLayout(inflater: LayoutInflater): ActivityDetailTargetBinding {
        return ActivityDetailTargetBinding.inflate(inflater)
    }

    override fun initialization() {
        getDataIntent()
        setupHistoryTarget()
        setOnClickListener()
//        swiperefreshLayout()
    }

    private fun getDataIntent() {
        target = intent.getParcelableExtra("TARGET")
    }

    override fun observeViewModel() {
        getData()
        viewModel.toDoneTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                     this.dismissLoading()
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
        viewModel.getDetailTarget(target?.id)
        viewModel.detailTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    this.dismissLoading()
                    val data = response.data?.data
                    setupView(data)
                    val dataHistory = response.data?.data?.user_targets
                    if (!dataHistory.isNullOrEmpty()) {
                        Log.e("IMAGE", "observeViewModel: $data")
//                        detailTargetAdapter.differ.submitList(dataHistory)
                        detailTargetAdapter.setData(dataHistory)

                    }

                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
                else -> {}
            }
        }
    }

    private fun setupView(data: DetailTargetItem?) {
        binding.root.isVisible = true
        binding.layoutTarget.apply {
            txtNamaTarget.text = data?.name
            txtDuration.text = "Target Tercapai ${data?.duration}"
            txtNominal.text = convertToRupiah(data?.nominal?.toDouble())
            txtCountdown.text = "${data?.count_day} Hari Lagi !"
            imgTarget.load(PATH_IMAGE + data?.image_url)

            val kakumpul = data?.remaining?.let { data.nominal?.minus(it) }
            txtTerkumpul.text = convertToRupiah(kakumpul?.toDouble())

            val percentage = (kakumpul!!.toDouble() / data.nominal!! * 100).toInt()
            txtPersentase.text = "$percentage%"
            progressBar.progress = percentage

        }
        binding.layoutRecommendation.apply {
            val recommendation = (data?.nominal!! / data.count_day!!).toDouble()
            txtRecommendation.text = convertToRupiah(recommendation)
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
            val dialog = TabungantargetDialog(target?.id, this)
            dialog.show(supportFragmentManager, "TABUNGANTARGET")
        }
        binding.btnTargetFinish.setOnClickListener {
            KAlertDialog(this@DetailTargetActivity, KAlertDialog.CUSTOM_IMAGE_TYPE, 0)
                .setTitleText("Target")
                .setContentText("Apakah Anda Yakin Target Sudah Selesai ?")
                .setConfirmText("Selesai")
                .setCustomImage(R.drawable.icon, this)
                .confirmButtonColor(R.drawable.background_btnblue, this)
                .setCancelText(getString(R.string.dlg_cancel))

                .setConfirmClickListener { dialog ->
                    dialog.apply {
                        viewModel.toDoneTarget(target?.id)
                        dismiss()
                        showSuccessConfirmationDialog()
                    }
                }
                .setCancelClickListener { dialog ->
                    dialog.apply {
                        dismiss()
                        cancel()

                    }
                }
                .show()
        }
    }

    private fun showSuccessConfirmationDialog() {
        KAlertDialog(this@DetailTargetActivity, KAlertDialog.SUCCESS_TYPE, 0)
            .setTitleText("Target Berhasil")
            .setContentText("Selamat Target Telah Tercapai")
            .setConfirmText("Selesai")
            .confirmButtonColor(R.drawable.background_btnblue, this)
            .setConfirmClickListener { dialog ->
                dialog.apply {
                    finish()
                }
            }.show()

    }

    override fun onItemClicked() {
        state = 2
        getData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        intent.putExtra("state", state)
        setResult(Activity.RESULT_OK, intent)
//        finish()
    }


}