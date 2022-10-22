package com.example.cuanku.screens.activity.book.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.developer.kalert.KAlertDialog
import com.example.cuanku.R
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.DialogAddBookDailyBinding
import com.example.cuanku.request.AddCatatanRequest
import com.example.cuanku.screens.activity.book.BookViewModel
import com.example.cuanku.utils.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookDailyDialog(
    private val id: String? = null,
    private val listener: onItemClickListener
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: DialogAddBookDailyBinding

    private val viewModel: BookViewModel by viewModels()
    lateinit var resultType: String
    private var loading: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAddBookDailyBinding.inflate(inflater, container, false)

        isCancelable = false

        observeViewModel()
        setupSpinner()
        onClickListener()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.addCatatan.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        dismissLoading()
                        listener.onItemClicked()
                        dismissAllowingStateLoss()
                        showSuccessConfirmationDialog()
                    }
                }
                is NetworkResult.Loading -> {
                    showLoading()
                }
                else -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkFormAddCatatan() {
        val request = AddCatatanRequest(
            saving_id = id,
            nominal = binding.edtNominal.value.toString(),
            type = resultType,
            detail = binding.edtDetailCatatan.text.toString()
        )
        viewModel.addCatatan(request)
    }

    private fun onClickListener() {
        binding.btnAddTarget.setOnClickListener {
            checkFormAddCatatan()
        }
        binding.imgCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.type,
            android.R.layout.simple_spinner_dropdown_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        resultType = p0?.getItemAtPosition(position).toString()
        resultType = if (resultType == "Pemasukan") {
            "in"
        } else {
            "out"
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
//        TODO("Not yet implemented")
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

    interface onItemClickListener {
        fun onItemClicked()
    }

}