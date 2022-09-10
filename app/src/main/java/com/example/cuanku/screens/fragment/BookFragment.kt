package com.example.cuanku.screens.fragment

import android.content.Intent
import android.view.LayoutInflater
import com.example.cuanku.base.BaseFragment
import com.example.cuanku.databinding.FragmentBookBinding
import com.example.cuanku.helper.AppManager
import com.example.cuanku.screens.activity.targets.add.AddTargetActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding>() {


    @Inject
    lateinit var userSession: AppManager

    override fun setLayout(inflater: LayoutInflater): FragmentBookBinding {
        return FragmentBookBinding.inflate(inflater)
    }

    override fun initialization() {
        setupView()
    }

    override fun observeViewModel() {

    }

    private fun setupView() {
        binding.txtName.text = userSession.getName(AppManager.PREFS_TOKEN)
        binding.txtName.setOnClickListener {
            startActivity(Intent(context, AddTargetActivity::class.java))
        }
    }


}