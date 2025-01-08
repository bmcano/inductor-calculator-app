package com.brandoncano.inductancecalculator.model.vtc

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandoncano.inductancecalculator.util.formatInductor
import com.brandoncano.inductancecalculator.util.isInvalidInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InductorVtcViewModel(context: Context): ViewModel() {

    private val repository = InductorVtcRepository.getInstance(context)

    private val _inductor = MutableStateFlow(InductorVtc())
    val inductor: StateFlow<InductorVtc> get() = _inductor

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    init {
        viewModelScope.launch {
            val loadedResistor = repository.loadInductor()
            _inductor.value = loadedResistor
            updateErrorState()
        }
    }

    fun clear() {
        _inductor.value = InductorVtc()
        _isError.value = false
        repository.clear()
    }

    fun updateValues(inductance: String, units: String, tolerance: String) {
        _inductor.value = _inductor.value.copy(
            inductance = inductance,
            units = units,
            tolerance = tolerance
        )
        updateErrorState()
        if (!_isError.value) {
            _inductor.value.formatInductor()
            saveInductorValues()
        }
    }
    private fun updateErrorState() {
        _isError.value = _inductor.value.isInvalidInput()
    }

    private fun saveInductorValues() {
        repository.saveInductor(_inductor.value)
    }
}
