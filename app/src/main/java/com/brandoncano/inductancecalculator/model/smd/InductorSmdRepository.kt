package com.brandoncano.inductancecalculator.model.smd

import android.content.Context
import com.brandoncano.inductancecalculator.data.SharedPreferences

class InductorSmdRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: InductorSmdRepository? = null
        fun getInstance(context: Context): InductorSmdRepository = instance
            ?: synchronized(this) {
                InductorSmdRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.CODE_INPUT_SMD.clearData(application)
        SharedPreferences.TOLERANCE_DROPDOWN_SMD.clearData(application)
    }

    fun loadInductor(): InductorSmd {
        val code = SharedPreferences.CODE_INPUT_SMD.loadData(application)
        val tolerance = SharedPreferences.TOLERANCE_DROPDOWN_SMD.loadData(application)
        return InductorSmd(code, tolerance)
    }

    fun saveInductor(inductor: InductorSmd) {
        SharedPreferences.CODE_INPUT_SMD.saveData(application, inductor.code)
        SharedPreferences.TOLERANCE_DROPDOWN_SMD.saveData(application, inductor.tolerance)
    }
}
