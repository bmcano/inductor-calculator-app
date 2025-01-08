package com.brandoncano.inductancecalculator.model.ctv

import android.content.Context
import com.brandoncano.inductancecalculator.data.SharedPreferences

class InductorCtvRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: InductorCtvRepository? = null
        fun getInstance(context: Context): InductorCtvRepository = instance
            ?: synchronized(this) {
                InductorCtvRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.BAND_ONE_CTV.clearData(application)
        SharedPreferences.BAND_TWO_CTV.clearData(application)
        SharedPreferences.BAND_THREE_CTV.clearData(application)
        SharedPreferences.BAND_FOUR_CTV.clearData(application)
    }

    fun loadInductor(): InductorCtv {
        val band1 = SharedPreferences.BAND_ONE_CTV.loadData(application)
        val band2 = SharedPreferences.BAND_TWO_CTV.loadData(application)
        val band3 = SharedPreferences.BAND_THREE_CTV.loadData(application)
        val band4 = SharedPreferences.BAND_FOUR_CTV.loadData(application)
        return InductorCtv(band1, band2, band3, band4)
    }

    fun saveInductor(inductor: InductorCtv) {
        SharedPreferences.BAND_ONE_CTV.saveData(application, inductor.band1)
        SharedPreferences.BAND_TWO_CTV.saveData(application, inductor.band2)
        SharedPreferences.BAND_THREE_CTV.saveData(application, inductor.band3)
        SharedPreferences.BAND_FOUR_CTV.saveData(application, inductor.band4)
    }
}
