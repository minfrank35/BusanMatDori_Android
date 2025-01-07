package com.example.busanmatdori.util

import android.content.Context
import android.content.SharedPreferences

/**
 * [공통] SharedPref 접근 유틸
 * @author 김성민
 * @since 2025/01/06
 */
object SharedPrefUtil {

    private const val PREF_NAME = "app_preferences"

    // SharedPreferences 인스턴스
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // 데이터를 저장하는 메서드
    fun saveString(context: Context, key: String, value: String) {
        val editor = getPreferences(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    // 데이터를 불러오는 메서드
    fun getString(context: Context, key: String, defaultValue: String? = null): String? {
        return getPreferences(context).getString(key, defaultValue)
    }

    // 데이터를 저장하는 메서드 (Int 타입)
    fun saveInt(context: Context, key: String, value: Int) {
        val editor = getPreferences(context).edit()
        editor.putInt(key, value)
        editor.apply()
    }

    // 데이터를 불러오는 메서드 (Int 타입)
    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
        return getPreferences(context).getInt(key, defaultValue)
    }

    // 데이터를 저장하는 메서드 (Boolean 타입)
    fun saveBoolean(context: Context, key: String, value: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    // 데이터를 불러오는 메서드 (Boolean 타입)
    fun getBoolean(context: Context, key: String, defaultValue: Boolean = false): Boolean {
        return getPreferences(context).getBoolean(key, defaultValue)
    }

    // 데이터를 삭제하는 메서드
    fun remove(context: Context, key: String) {
        val editor = getPreferences(context).edit()
        editor.remove(key)
        editor.apply()
    }

    // 모든 데이터를 삭제하는 메서드
    fun clear(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
