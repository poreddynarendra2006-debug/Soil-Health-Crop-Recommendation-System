package com.cropapp.recommendation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val crop: String) : UiState()
    data class Error(val message: String) : UiState()
}

class CropViewModel : ViewModel() {

    private val repository = CropRepository()

    private val _uiState = MutableLiveData<UiState>(UiState.Idle)
    val uiState: LiveData<UiState> = _uiState

    fun predictCrop(
        n: Int, p: Int, k: Int,
        temperature: Double, humidity: Double,
        ph: Double, rainfall: Double
    ) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            val result = repository.getCropRecommendation(n, p, k, temperature, humidity, ph, rainfall)
            result.fold(
                onSuccess = { crop ->
                    _uiState.value = UiState.Success(crop)
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error occurred")
                }
            )
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}
