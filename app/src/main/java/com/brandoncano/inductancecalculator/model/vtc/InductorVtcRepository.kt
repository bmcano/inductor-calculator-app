package com.brandoncano.inductancecalculator.model.vtc

import android.content.Context
import com.brandoncano.inductancecalculator.data.SharedPreferences

class InductorVtcRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: InductorVtcRepository? = null
        fun getInstance(context: Context): InductorVtcRepository = instance
            ?: synchronized(this) {
                InductorVtcRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.USER_INPUT_VTC.clearData(application)
        SharedPreferences.UNITS_DROPDOWN_VTC.clearData(application)
        SharedPreferences.TOLERANCE_DROPDOWN_VTC.clearData(application)
    }

    fun loadInductor(): InductorVtc {
        val input = SharedPreferences.USER_INPUT_VTC.loadData(application)
        val units = SharedPreferences.UNITS_DROPDOWN_VTC.loadData(application)
        val tolerance = SharedPreferences.TOLERANCE_DROPDOWN_VTC.loadData(application)
        return InductorVtc(input, units, tolerance)
    }

    fun saveInductor(inductor: InductorVtc) {
        SharedPreferences.USER_INPUT_VTC.saveData(application, inductor.inductance)
        SharedPreferences.UNITS_DROPDOWN_VTC.saveData(application, inductor.units)
        SharedPreferences.TOLERANCE_DROPDOWN_VTC.saveData(application, inductor.tolerance)
    }
}
