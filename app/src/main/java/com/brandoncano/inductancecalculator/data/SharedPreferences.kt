package com.brandoncano.inductancecalculator.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Job: Holds the names, keys, and methods for all shared_prefs data.
 *
 * Notes:
 *   Data is saved as xml files with mapping, where name_ -> file name; key_ -> key in map.
 *   Device File Explorer -> data -> data -> com.brandoncano.inductancecalculator -> shared_prefs
 */
enum class SharedPreferences(private val _name: String, private val _key: String) {

    BAND_ONE_CTV("color_to_value", "band_1"),
    BAND_TWO_CTV("color_to_value", "band_2"),
    BAND_THREE_CTV("color_to_value", "band_3"),
    BAND_FOUR_CTV("color_to_value", "band_4"),

    USER_INPUT_VTC("value_to_color", "user_input"),
    UNITS_DROPDOWN_VTC("value_to_color", "units_dropdown"),
    TOLERANCE_DROPDOWN_VTC("value_to_color", "tolerance_dropdown"),

    CODE_INPUT_SMD("smd", "code_input"),
    TOLERANCE_DROPDOWN_SMD("smd", "tolerance_dropdown"),

    ; // methods to save, load, or clear the data as strings

    fun saveData(context: Context, input: String) {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(input)
        editor.putString(_key, json)
        editor.apply()
    }

    fun loadData(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(_key, null)
        val type: Type = object : TypeToken<String?>() {}.type
        return gson.fromJson<String?>(json, type) ?: return ""
    }

    fun clearData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }
}
