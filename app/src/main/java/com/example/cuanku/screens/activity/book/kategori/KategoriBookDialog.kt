package com.example.cuanku.screens.activity.book.kategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.developer.kalert.KAlertDialog
import com.example.cuanku.R
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.DialogKategoribookBinding
import com.example.cuanku.request.AddKategoriBookRequest
import com.example.cuanku.screens.activity.book.BookViewModel
import com.example.cuanku.utils.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KategoriBookDialog(
    private val listener: OnItemClickListener
) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogKategoribookBinding
    private val viewModel: BookViewModel by viewModels()
    private var loading: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogKategoribookBinding.inflate(inflater, container, false)

        isCancelable = false
        observeViewModel()
        setOnClikListener()

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun observeViewModel() {
        viewModel.addKategori.observe(this) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        dismissLoading()
                        dismissAllowingStateLoss()
                        showSuccessConfirmationDialog()

                    }
                }
                is NetworkResult.Loading -> {
                    showLoading()
                }

                else -> {}
            }
        }
    }

    private fun checkFormAddKategori() {
        val request = AddKategoriBookRequest(
            name = binding.edtKategori.text.toString(),
            image = "icon"
        )
        viewModel.addKategori(request)
    }

    private fun setOnClikListener() {
        binding.btnAddKategori.setOnClickListener {
            checkFormAddKategori()
        }
        binding.imgCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    interface OnItemClickListener {
        fun onItemClicked()
    }

    private fun showSuccessConfirmationDialog() {
        KAlertDialog(requireContext(), KAlertDialog.SUCCESS_TYPE, 0)
            .setTitleText("Catatan Berhasil")
            .setContentText("Catatan telah ditambahkan")
            .setConfirmText("Selesai")
            .confirmButtonColor(R.drawable.background_btnblue, requireContext())
            .setConfirmClickListener { dialog ->
                dialog.apply {
                    dismiss()
                    listener.onItemClicked()
                }
            }.show()

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