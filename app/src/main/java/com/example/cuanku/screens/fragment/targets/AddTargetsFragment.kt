package com.example.cuanku.screens.fragment.targets

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.cuanku.R
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.FragmentAddTargetsBinding
import com.example.cuanku.request.AddTargetRequest
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddTargetsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddTargetsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TargetsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTargetsBinding.inflate(inflater, container, false)
        val view = binding.root
        setonClickListener()

        observeViewModel()
        return view
    }

    private fun observeViewModel() {
        viewModel.addTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        Toast.makeText(
                            context,
                            "Data Berhasil Ditambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                        dismiss()
                    }
                }
            }

        }
    }


    private fun checkFormAddTarget() {
        val request = AddTargetRequest(
            name = binding.edtNameTarget.text.toString(),
            duration = binding.edtDate.text.toString(),
            remaining = null,
            nominal = binding.edtHarga.value.toInt()
        )
        viewModel.addTarget(request)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
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
            requireContext(), { _, myear, mmonth, mdayOfMonth ->
                val uiFormat = "dd MMMM yyyy"
                val sdf = SimpleDateFormat(uiFormat, Locale.getDefault())
//                val selectedDate = sdf.format()
//                binding.edtDate.text = selectedDate
                binding.edtDate.setText("$mdayOfMonth-$mmonth-$myear")
            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }



}