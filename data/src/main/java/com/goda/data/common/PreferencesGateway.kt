package com.goda.data.common

import android.content.SharedPreferences


val preferencesGateway by lazy { PreferencesGateway() }

const val PREFERENCES_NAME = "_PREFERENCE"
const val KEY_USER_ID = "user_id"

const val KEY_TOKEN = "token"
const val KEY_REFRESH_TOKEN = "refresh_token"

class PreferencesGateway {



}

inline fun <reified T : Any> SharedPreferences.Editor.putValue(
    key: String,
    value: T
) {
    when (T::class) {
        Boolean::class -> putBoolean(key, value as Boolean)
        Int::class -> putInt(key, value as Int)
        Long::class -> putLong(key, value as Long)
        Float::class -> putFloat(key, value as Float)
        String::class -> putString(key, value as String)
        else -> throw UnsupportedOperationException("not supported preferences type")
    }
}

inline fun <reified T : Any> SharedPreferences.getValue(
    key: String,
    defaultValue: T
): T {
    return when (T::class) {
        Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
        Int::class -> getInt(key, defaultValue as Int) as T
        Long::class -> getLong(key, defaultValue as Long) as T
        Float::class -> getFloat(key, defaultValue as Float) as T
        String::class -> getString(key, defaultValue as String) as T
        // FilterLongTermBody::class -> Gson().fromJson(key, defaultValue as FilterLongTermBody::java.class) as T
        // FilterShortTermBody::class -> GsonBuilder().create().fromJson(key, T::class.java) as T
        else -> throw UnsupportedOperationException("not supported preferences type")
    }
}



fun SharedPreferences.setUserData(userId: String, token: String, refreshToken: String) {
    this
        .edit()
        .apply { putValue(KEY_USER_ID, userId) }
        .apply { putValue(KEY_REFRESH_TOKEN, refreshToken) }
        .apply { putValue(KEY_TOKEN, token) }
        .apply()

}


