package com.example.tote_fifa_2022.utilits

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val IS_AUTH = "isAuth"
    private const val APP_PREF = "toteSettings"

    private lateinit var settings: SharedPreferences

    fun getPreference(context: Context): SharedPreferences {
        settings = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
        return settings
    }

    fun setAuth(auth: Boolean) {
        settings.edit()
            .putBoolean(IS_AUTH, auth)
            .apply()
    }

    fun getAuth(): Boolean {
        return settings.getBoolean(IS_AUTH, false)
    }
}