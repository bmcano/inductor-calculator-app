package com.brandoncano.inductancecalculator.adapter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.brandoncano.inductancecalculator.keys.AppAppearance
import com.brandoncano.inductancecalculator.keys.SharedPreferencesKey
import com.brandoncano.inductancecalculator.to.InductorCtv
import com.brandoncano.inductancecalculator.ui.MainApplication
import com.google.gson.Gson

/**
 * Notes:
 * + Device File Explorer: `/data/data/com.brandoncano.resistancecalculator/shared_prefs/ResistorSharedPrefs.xml`
 */
class SharedPreferencesAdapter {

    private companion object {
        const val NAME = "ResistorSharedPrefs"
    }

    fun getResetPreferences(): Boolean {
        return getBoolean(SharedPreferencesKey.KEY_RESET_PREFERENCES, true)
    }

    fun setResetPreferences() {
        setBoolean(SharedPreferencesKey.KEY_RESET_PREFERENCES, false)
    }

    fun getAppAppearancePreference(): String {
        val default = AppAppearance.SYSTEM_DEFAULT.toString()
        val pref = getString(SharedPreferencesKey.KEY_APP_APPEARANCE, default)
        return pref ?: default
    }

    fun setAppAppearancePreference(appAppearance: String) {
        setString(SharedPreferencesKey.KEY_APP_APPEARANCE, appAppearance)
    }

    fun getDynamicColorPreference(): Boolean {
        return getBoolean(SharedPreferencesKey.KEY_DYNAMIC_COLOR, false)
    }

    fun setDynamicColorPreference(dynamicColor: Boolean) {
        setBoolean(SharedPreferencesKey.KEY_DYNAMIC_COLOR, dynamicColor)
    }

    fun getInductorCtvPreference(): InductorCtv {
        val inductor = getString(SharedPreferencesKey.KEY_COLOR_TO_VALUE, null)
        return inductor.fromJsonStringToTypeOrNull() ?: InductorCtv()
    }

    fun setInductorCtvPreference(inductor: InductorCtv) {
        val inductorString = Gson().toJson(inductor, InductorCtv::class.java)
        setString(SharedPreferencesKey.KEY_COLOR_TO_VALUE, inductorString)
    }

    fun removeSharedPreference(sharedPreferencesKey: SharedPreferencesKey) {
        getSharedPreferences().edit {
            remove(sharedPreferencesKey.key)
        }
    }

    private fun getString(sharedPreferencesKey: SharedPreferencesKey, default: String?): String? {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getString(sharedPreferencesKey.key, default)
    }

    private fun setString(sharedPreferencesKey: SharedPreferencesKey, value: String?) {
        getSharedPreferences().edit {
            putString(sharedPreferencesKey.key, value)
        }
    }

    private fun getBoolean(sharedPreferencesKey: SharedPreferencesKey, default: Boolean): Boolean {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getBoolean(sharedPreferencesKey.key, default)
    }

    private fun setBoolean(sharedPreferencesKey: SharedPreferencesKey, value: Boolean) {
        getSharedPreferences().edit {
            putBoolean(sharedPreferencesKey.key, value)
        }
    }

    private fun getSharedPreferences(): SharedPreferences {
        val application = MainApplication.instance
        return application.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    private inline fun <reified T : Any> String?.fromJsonStringToTypeOrNull(): T? {
        if (this == null) return null
        if (T::class == String::class) return this as T
        return try {
            Gson().fromJson(this, T::class.java)
        } catch (ex: Exception) {
            Log.e(NAME, Log.getStackTraceString(ex))
            null
        }
    }
}
