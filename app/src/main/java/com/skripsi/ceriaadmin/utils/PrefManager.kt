package com.skripsi.ceriaadmin.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.skripsi.ceriaadmin.R
import com.skripsi.ceriaadmin.api.response.LoginData

class PrefManager(context: Context) {
    private var s: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER = "user"
        const val TOKEN = "token"
    }

    fun setData(value: LoginData) {
        val data: String = Gson().toJson(value, LoginData::class.java)
        s.edit().putString(USER, data).apply()
    }

    fun getData(): LoginData? {
        val data: String = s.getString(USER, null) ?: return null
        return Gson().fromJson(data, LoginData::class.java)
    }

    fun saveToken(token: String) {
        s.edit().putString(TOKEN, token).apply()
    }

    fun fetchToken(): String? {
        return s.getString(TOKEN, null)
    }

    fun deleteToken() {
        s.edit().clear().apply()
    }
}