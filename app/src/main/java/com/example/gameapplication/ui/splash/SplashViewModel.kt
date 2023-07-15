package com.example.gameapplication.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapplication.domain.model.ApplicationMode
import com.example.gameapplication.domain.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _modeLiveData = MutableStateFlow<ApplicationMode>(ApplicationMode.Undefined)
    val modeLiveData: StateFlow<ApplicationMode> = _modeLiveData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _modeLiveData.value = repository.checkMode()
            _isLoading.value = false
        }
    }
}