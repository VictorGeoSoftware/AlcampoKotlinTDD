package com.victor.test.alcampokotlin.utils

import android.content.Context
import android.os.Build
import java.util.*

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */
class UniqueId(val context: Context) {
    companion object {
        const val UNIQUE_ID = "UNIQUE_ID"
    }


    fun getUniqueId(): String {
        val pref = context.getSharedPreferences(UNIQUE_ID, Context.MODE_PRIVATE)
        var uniqueId = pref.getString(UNIQUE_ID, "")

        if (uniqueId == "") { uniqueId = getNewUniqueId() }

        return uniqueId
    }

    fun getNewUniqueId(): String {
        val newUniqueId = Build.ID + "" + Date().time
//        setUniqueId(newUniqueId)
        return newUniqueId
    }

    private fun setUniqueId(uniqueId: String) {
//    private fun setUniqueId(uniqueId: String, sharedPreferences: SharedPreferences) {
        val pref = context.getSharedPreferences(UNIQUE_ID, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(UNIQUE_ID, uniqueId)
        editor.apply()
    }
}