package com.tnh.testchart.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tnh.tnhlibrary.liveData.utils.toLiveData
import com.tnh.tnhlibrary.utils.logAny
import com.tnh.tnhlibrary.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel() {
    private val _testLiveData = MutableLiveData<String>("Test live data")
    val testLiveData = _testLiveData.toLiveData()

    val intervalFlow = flow {
        for (i in 0..5){
            emit(i)
            delay(1000)
        }
    }

    var test = "HomeFragment"

    private val _uiState = MutableStateFlow("State flow")
    val uiState: StateFlow<String> = _uiState

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _sharedFlow.emit("Welcome!!!")
        }
        test = "INIT ONCE"
    }
}