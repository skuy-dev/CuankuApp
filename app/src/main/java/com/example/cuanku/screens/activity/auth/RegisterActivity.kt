package com.example.cuanku.screens.activity.auth

import android.content.Intent
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityRegisterBinding
import com.example.cuanku.request.RegisterRequest
import com.example.cuanku.screens.activity.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun setLayout(inflater: LayoutInflater): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(inflater)
    }

    override fun initialization() {
        onClickListener()
    }

    override fun observeViewModel() {
        viewModel.register.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkFormRegister() {
        val request = RegisterRequest(
            name = binding.layoutRegister.edtName.text.toString(),
            email = binding.layoutRegister.edtEmail.text.toString(),
            phone_number = binding.layoutRegister.edtPhone.text.toString(),
            password = binding.layoutRegister.edtPassword.text.toString()
        )
        viewModel.postRegister(request)
    }

    private fun onClickListener() {
        binding.btnRegister.setOnClickListener {
            checkFormRegister()
        }
    }

}