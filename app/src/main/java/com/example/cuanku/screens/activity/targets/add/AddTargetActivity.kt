package com.example.cuanku.screens.activity.targets.add

import android.app.DatePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cuanku.R
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityAddTargetBinding
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.example.cuanku.screens.fragment.targets.TargetsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddTargetActivity : BaseActivity<ActivityAddTargetBinding>() {

    private val viewModel: TargetsViewModel by viewModels()

    override fun setLayout(inflater: LayoutInflater): ActivityAddTargetBinding {
        return ActivityAddTargetBinding.inflate(inflater)
    }

    override fun initialization() {
        setonClickListener()
    }

    override fun observeViewModel() {
        viewModel.addTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        Toast.makeText(
                            this,
                            "Data Berhasil Ditambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(applicationContext, TargetsFragment::class.java))
                        finish()
                    }
                }
            }

        }
    }

    private fun checkFormAddTarget() {
        val request = AddTargetRequest(
            name = binding.edtNameTarget.text.toString(),
//            duration = binding.edtDate.text.toString(),
            duration = "27-09-2020",
            images_url = R.drawable.ic_add.toString(),
            remaining = null,
            nominal = binding.edtHarga.value.toInt()
        )
        viewModel.addTarget(request)
    }


    private fun setonClickListener() {

        binding.btnAddTarget.setOnClickListener {
            checkFormAddTarget()
        }

        binding.btnAddDate.setOnClickListener {
            showDatePickerDialog()
        }

    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            applicationContext, { _, myear, mmonth, mdayOfMonth ->

                val simpleDateFormat =
                    SimpleDateFormat("EEEE, d MMMM yy", Locale.getDefault())
                simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                val date = Date(myear, mmonth, mdayOfMonth)
                val dayString = simpleDateFormat.format(date)
                binding.edtDate.text = dayString
            },
            year, month, day
        )
        datePickerDialog.show()
    }

//    private fun showDatePickerDialog() {
//        val cal = Calendar.getInstance()
//        val year = cal.get(Calendar.YEAR)
//        val month = cal.get(Calendar.MONTH)
//        val day = cal.get(Calendar.DAY_OF_MONTH)
//        val datePickerDialog = DatePickerDialog(
//            applicationContext, { _, myear, mmonth, mdayOfMonth ->
//
//                binding.edtDate.setText("$mdayOfMonth-$mmonth-$myear")
//            },
//            year, month, day
//        )
//        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
//        datePickerDialog.show()
//    }

}