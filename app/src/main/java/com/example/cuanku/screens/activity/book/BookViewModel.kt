package com.example.cuanku.screens.activity.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.repository.BookRepository
import com.example.cuanku.request.AddCatatanRequest
import com.example.cuanku.request.AddKategoriBookRequest
import com.example.cuanku.response.AddCatatanResponse
import com.example.cuanku.response.DailyCatatanResponse
import com.example.cuanku.response.KategoriBookResponse
import com.example.cuanku.response.ListKategoriBookResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    private val _addCatatan = MutableLiveData<NetworkResult<AddCatatanResponse>>()
    val addCatatan: LiveData<NetworkResult<AddCatatanResponse>> = _addCatatan

    fun addCatatan(request: AddCatatanRequest) = viewModelScope.launch {
        repository.addCatatan(request)
            .collect {
                _addCatatan.value = it
            }
    }

    private val _getDailyCatatan = MutableLiveData<NetworkResult<DailyCatatanResponse>>()
    val getDailyCatatan: LiveData<NetworkResult<DailyCatatanResponse>> = _getDailyCatatan

    fun getDailyCatatan() = viewModelScope.launch {
        repository.getDailyCatatan()
            .collect {
                _getDailyCatatan.value = it
            }
    }

    private val _addKategori = MutableLiveData<NetworkResult<KategoriBookResponse>>()
    val addKategori : LiveData<NetworkResult<KategoriBookResponse>> = _addKategori

    fun addKategori(request: AddKategoriBookRequest) = viewModelScope.launch {
        repository.addKategori(request)
            .collect {
                _addKategori.value = it
            }
    }

    private val _getListKategori = MutableLiveData<NetworkResult<ListKategoriBookResponse>>()
    val getListKategori : LiveData<NetworkResult<ListKategoriBookResponse>> = _getListKategori

    fun getListKategori() = viewModelScope.launch {
        repository.getListKategori()
            .collect {
                _getListKategori.value = it
            }
    }

}