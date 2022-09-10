package com.example.cuanku.screens.activity.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cuanku.R
import com.example.cuanku.databinding.FragmentDashboardDialogBinding
import com.example.cuanku.screens.activity.targets.add.AddTargetActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardDialogFragment(context: Context) : BottomSheetDialogFragment() {

    private var _binding: FragmentDashboardDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        setOnClickListener()


        return view
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun setOnClickListener() {
        binding.btnTarget.setOnClickListener {
            startActivity(Intent(context, AddTargetActivity::class.java))
            dismissAllowingStateLoss()
        }
    }

}