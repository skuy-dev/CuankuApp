package com.example.cuanku.screens.activity.book.add

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.developer.kalert.KAlertDialog
import com.example.cuanku.R
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityAddBookBinding
import com.example.cuanku.request.AddCatatanRequest
import com.example.cuanku.screens.activity.book.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookActivity : BaseActivity<ActivityAddBookBinding>(), AdapterView.OnItemSelectedListener {

    private val viewModel: BookViewModel by viewModels()

    lateinit var resultType: String

    override fun setLayout(inflater: LayoutInflater): ActivityAddBookBinding {
        return ActivityAddBookBinding.inflate(inflater)
    }

    override fun initialization() {
        onClickListener()
        setupSpinner()
    }

    override fun observeViewModel() {
        viewModel.addCatatan.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        dismissLoading()
                        showSuccessConfirmationDialog()
                    }
                }
                is NetworkResult.Loading -> {
                    showLoading()
                }
                else -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.type,
            android.R.layout.simple_spinner_dropdown_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = this
    }

    private fun checkFormAddCatatan() {

        val nominal = binding.edtNominal.value.toString()

        val request = AddCatatanRequest(
            saving_id = "1",
            nominal = nominal,
            type = resultType
        )
        viewModel.addCatatan(request)
    }

    private fun onClickListener() {
        binding.btnAddTarget.setOnClickListener {
            checkFormAddCatatan()
        }
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

    }

    private fun showSuccessConfirmationDialog() {
        KAlertDialog(this@AddBookActivity, KAlertDialog.SUCCESS_TYPE, 0)
            .setTitleText("Catatan Berhasil")
            .setContentText("Catatan telah ditambahkan")
            .setConfirmText("Selesai")
            .confirmButtonColor(R.drawable.background_btnblue, this)
            .setConfirmClickListener { dialog ->
                dialog.apply {
                    finish()
                }
            }.show()

    }

}