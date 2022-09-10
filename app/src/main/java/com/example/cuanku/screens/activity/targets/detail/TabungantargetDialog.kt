package com.example.cuanku.screens.activity.targets.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.cuanku.R
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.DialogTabungantargetBinding
import com.example.cuanku.databinding.FragmentDashboardDialogBinding
import com.example.cuanku.helper.RefreshOnResumeEvent
import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.response.DataListTargets
import com.example.cuanku.response.DetailTargetResponse
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.example.cuanku.screens.fragment.targets.TargetsFragment
import com.example.cuanku.utils.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus

@AndroidEntryPoint
class TabungantargetDialog(
    private val id: Int? = null
) : BottomSheetDialogFragment() {

    private val viewModel: TargetsViewModel by viewModels()

    private lateinit var binding: DialogTabungantargetBinding
    private var loading: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogTabungantargetBinding.inflate(inflater, container, false)

        isCancelable = false

        observeViewModel()
        onClickListener()

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun observeViewModel() {
        viewModel.tabunganTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        dismissLoading()
                        EventBus.getDefault().post(RefreshOnResumeEvent(true))
                        Toast.makeText(context, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT)
                            .show()
                        dismissAllowingStateLoss()

                    } else Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    showLoading()
                }
            }
        }
    }


    private fun onClickListener() {
        binding.btnAddTabunganTarget.setOnClickListener {
            checkFormAddTabunganTarget()
        }
        binding.imgCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun checkFormAddTabunganTarget() {
        val request = AddTabunganTargetRequest(
            id,
            binding.edtNominal.value.toInt()
        )
        viewModel.addTabunganTarget(request)
    }


    private fun showLoading() {
        if (loading == null)
            loading = LoadingDialog()

        loading?.show(parentFragmentManager, "LOADING_DIALOG")

    }

    private fun dismissLoading() {
        loading?.dismissAllowingStateLoss()
    }

}