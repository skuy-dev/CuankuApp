package com.example.cuanku.screens.fragment.targets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.repository.TargetsRepository
import com.example.cuanku.response.ListTargetsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TargetsViewModel @Inject constructor(private val repository: TargetsRepository) :
    ViewModel() {

    private val _listTargets = MutableLiveData<NetworkResult<ListTargetsResponse>>()
    val listTargets: LiveData<NetworkResult<ListTargetsResponse>> = _listTargets

    fun getListTargets(token: String) = viewModelScope.launch {
        repository.getListTargets(token)
            .collect {
                _listTargets.value = it
            }
    }

}