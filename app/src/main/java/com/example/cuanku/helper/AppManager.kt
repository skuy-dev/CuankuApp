package com.example.cuanku.helper

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppManager @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val PREFS_APP = "APPNAME"
        const val PREFS_NAME = "NAME"
        const val PREFS_TOKEN = "TOKEN"
    }

    private val preference = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE)

    val editor = preference.edit()

    fun login(token: String, name: String) {
        editor.putString(PREFS_TOKEN, token).commit()
        editor.putString(PREFS_NAME, name).commit()
        editor.apply()
    }

    fun isLogin(): String? {
        return preference.getString(PREFS_TOKEN, "")
    }

    fun getName(name: String): String? {
        return preference.getString(name, null)
    }

    fun getToken(): String? {
        return preference.getString(PREFS_TOKEN,"")
    }

//    fun getToken(token: String): String? {
//        return preference.getString(token, null)
//    }

}