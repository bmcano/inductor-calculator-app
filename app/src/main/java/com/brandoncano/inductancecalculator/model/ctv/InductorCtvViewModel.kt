package com.brandoncano.inductancecalculator.model.ctv

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InductorCtvViewModel(context: Context): ViewModel() {

    private val repository = InductorCtvRepository.getInstance(context)

    private val _inductor = MutableStateFlow(InductorCtv())
    val inductor: StateFlow<InductorCtv> get() = _inductor

    init {
        viewModelScope.launch {
            val loadedInductor = repository.loadInductor()
            _inductor.value = loadedInductor
        }
    }

    fun clear() {
        _inductor.value = InductorCtv()
        repository.clear()
    }

    fun updateBand(bandNumber: Int, color: String) {
        _inductor.value = when (bandNumber) {
            1 -> _inductor.value.copy(band1 = color)
            2 -> _inductor.value.copy(band2 = color)
            3 -> _inductor.value.copy(band3 = color)
            4 -> _inductor.value.copy(band4 = color)
            else -> _inductor.value
        }
        repository.saveInductor(_inductor.value)
    }
}
