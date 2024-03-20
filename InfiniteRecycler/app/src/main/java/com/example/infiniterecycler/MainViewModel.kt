package com.example.infiniterecycler

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = Repository()

    private val _contentLiveData = MutableLiveData<DataList>()
    val contentLiveData: LiveData<DataList> = _contentLiveData

    fun getContent(page: Int) = viewModelScope.launch {
        runCatching { repository.getContent(page) }
            .onSuccess { _contentLiveData.value = it.body()?.data }
            .onFailure { Log.d("ViewModel", "Content 에러: ${it.message}") }
    }

}