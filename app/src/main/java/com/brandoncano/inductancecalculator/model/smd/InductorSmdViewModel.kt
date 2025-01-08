package com.brandoncano.inductancecalculator.model.smd

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandoncano.inductancecalculator.util.formatInductance
import com.brandoncano.inductancecalculator.util.isSmdInputInvalid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InductorSmdViewModel(context: Context): ViewModel() {

    private val repository = InductorSmdRepository.getInstance(context)

    private val _inductor = MutableStateFlow(InductorSmd())
    val inductor: StateFlow<InductorSmd> get() = _inductor

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    init {
        viewModelScope.launch {
            val loadedInductor = repository.loadInductor()
            _inductor.value = loadedInductor
            updateErrorState()
        }
    }

    fun clear() {
        _inductor.value = InductorSmd()
        _isError.value = false
        repository.clear()
    }

    fun updateValues(code: String, tolerance: String) {
        _inductor.value = _inductor.value.copy(code = code, tolerance = tolerance)
        updateErrorState()
        if (!_isError.value) {
            _inductor.value.formatInductance()
            saveInductorValues()
        }
    }

    private fun saveInductorValues() {
        repository.saveInductor(_inductor.value)
    }

    private fun updateErrorState() {
        _isError.value = _inductor.value.isSmdInputInvalid()
    }
}
