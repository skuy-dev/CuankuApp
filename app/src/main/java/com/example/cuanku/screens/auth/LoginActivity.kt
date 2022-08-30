package com.example.cuanku.screens.auth

import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityLoginBinding
import com.example.cuanku.helper.*
import com.example.cuanku.request.LoginRequest
import com.example.cuanku.screens.dasboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    @Inject
    lateinit var saveSession: AppManager

    private val viewModel: AuthViewModel by viewModels()

    override fun setLayout(inflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(inflater)
    }

    override fun initialization() {
        setupView()
        onClickListener()
    }

    override fun observeViewModel() {
        viewModel.login.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        saveSession.login(
                            response.data.data?.token.toString(),
                            response.data.data?.user?.name.toString()
                        )
                        startActivity(Intent(this, DashboardActivity::class.java))
                    }
                }
            }
        }
    }

    private fun setupView() {
        binding.layoutLogin.apply {
            tilName.visibility = GONE
            tilPhone.visibility = GONE
            btnAuth.text = "Login"
        }

        setupTextInputWatcher()

    }

    private fun checkFormLogin() {
        val request = LoginRequest(
            email = binding.layoutLogin.edtEmail.text.toString(),
            password = binding.layoutLogin.edtPassword.text.toString()
        )
        viewModel.postLogin(request)
    }

    private fun setupTextInputWatcher() {
        binding.layoutLogin.apply {
            edtEmail.doAfterTextChanged {
                val isEmailValid = it.toString().isEmailValid()
                if (isEmailValid) {
                    tilEmail.error = null
                } else {
                    tilEmail.error = "Email Tidak Valid"
                }
            }

            edtPassword.doAfterTextChanged {
                if (tilPassword.error?.isNotBlank() == true) {
                    tilPassword.error = null
                }
                checkPasswordValidation(it.toString())
            }

        }
    }

    private fun checkPasswordValidation(password: String) {
        if (password.isBlank()) {
            binding.layoutLogin.tilPassword.error = null
        } else {
            val lengthValid = isMinimalPasswordLength(password)
            val uppercaseValid = isPasswordContainUppercase(password)
            val lowercaseValid = isPasswordContainLowercase(password)
            val numberValid = isPasswordContainNumber(password)

            if (lengthValid && uppercaseValid && lowercaseValid && numberValid) {
                binding.layoutLogin.tilPassword.error = null
            } else {
                binding.layoutLogin.tilPassword.error =
                    "Kata Sandi harus mengandung 8 Karakter, huruf besar, huruf kecil dan angka"
            }

        }
    }

    private fun onClickListener() {
        binding.layoutLogin.btnAuth.setOnClickListener {
            checkFormLogin()
        }
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

}