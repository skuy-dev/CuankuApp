package com.example.cuanku.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.repository.AuthRepository
import com.example.cuanku.request.LoginRequest
import com.example.cuanku.request.RegisterRequest
import com.example.cuanku.response.LoginResponse
import com.example.cuanku.response.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _login = MutableLiveData<NetworkResult<LoginResponse>>()
    val login: LiveData<NetworkResult<LoginResponse>> = _login

    fun postLogin(request: LoginRequest) = viewModelScope.launch {
        repository.postLogin(request)
            .collect {
                _login.value = it
            }
    }

    private val _register = MutableLiveData<NetworkResult<RegisterResponse>>()
    val register: LiveData<NetworkResult<RegisterResponse>> = _register

    fun postRegister(request: RegisterRequest) = viewModelScope.launch {
        repository.postRegister(request)
            .collect {
                _register.value = it
            }
    }

}