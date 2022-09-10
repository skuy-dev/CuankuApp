package com.example.cuanku.screens.activity.targets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.repository.TargetsRepository
import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.response.AddTabunganTargetResponse
import com.example.cuanku.response.AddTargetResponse
import com.example.cuanku.response.DetailTargetResponse
import com.example.cuanku.response.ListTargetsResponse
import com.example.cuanku.screens.activity.targets.detail.DetailTargetActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
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

//    fun addTarget(request: AddTargetRequest) = viewModelScope.launch {
//        repository.addTargets(request)
//            .collect {
//                _addTarget.value = it
//            }
//    }

    fun addTarget(
        file: MultipartBody.Part,
        name: RequestBody,
        duration: RequestBody,
        remaining: RequestBody,
        nominal: RequestBody
    ) = viewModelScope.launch {
        repository.addTargets(file, name, duration, remaining, nominal)
            .collect {
                _addTarget.value = it
            }
    }

//    fun addTarget(
//        name: MultipartBody.Part,
//        duration: MultipartBody.Part,
//        remaining: MultipartBody.Part,
//        image: MultipartBody.Part,
//        nominal: MultipartBody.Part
//    ) = viewModelScope.launch {
//        repository.addTargets(
//            name, duration, remaining, image, nominal
//        )
//            .collect {
//                _addTarget.value = it
//            }
//    }

    private val _detailTarget = MutableLiveData<NetworkResult<DetailTargetResponse>>()
    val detailTarget: LiveData<NetworkResult<DetailTargetResponse>> = _detailTarget

    fun getDetailTarget(id: Int?) = viewModelScope.launch {
        repository.getDetailTarget(id)
            .collect {
                _detailTarget.value = it
            }
    }

    private val _addTabunganTarget = MutableLiveData<NetworkResult<AddTabunganTargetResponse>>()
    val tabunganTarget: LiveData<NetworkResult<AddTabunganTargetResponse>> = _addTabunganTarget

    fun addTabunganTarget(request: AddTabunganTargetRequest) = viewModelScope.launch {
        repository.addTabunganTarget(request)
            .collect {
                _addTabunganTarget.value = it
            }
    }

}