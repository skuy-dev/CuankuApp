package com.example.cuanku.screens.activity.targets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.repository.TargetsRepository
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.response.AddTargetResponse
import com.example.cuanku.response.DetailTargetResponse
import com.example.cuanku.response.ListTargetsResponse
import com.example.cuanku.screens.activity.targets.detail.DetailTargetActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TargetsViewModel @Inject constructor(private val repository: TargetsRepository) :
    ViewModel() {

    private val _listTargets = MutableLiveData<NetworkResult<ListTargetsResponse>>()
    val listTargets: LiveData<NetworkResult<ListTargetsResponse>> = _listTargets

    fun getListTargets() = viewModelScope.launch {
        repository.getListTargets()
            .collect {
                _listTargets.value = it
            }
    }

    private val _addTarget = MutableLiveData<NetworkResult<AddTargetResponse>>()
    val addTarget: LiveData<NetworkResult<AddTargetResponse>> = _addTarget

    fun addTarget(request: AddTargetRequest) = viewModelScope.launch {
        repository.addTargets(request)
            .collect {
                _addTarget.value = it
            }
    }

    private val _detailTarget = MutableLiveData<NetworkResult<DetailTargetResponse>>()
    val detailTarget: LiveData<NetworkResult<DetailTargetResponse>> = _detailTarget

    fun getDetailTarget(id: Int?) = viewModelScope.launch {
        repository.getDetailTarget(id)
            .collect {
                _detailTarget.value = it
            }
    }

}