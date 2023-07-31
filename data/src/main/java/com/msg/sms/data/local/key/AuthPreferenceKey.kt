package com.msg.sms.data.local.key

import androidx.datastore.preferences.core.stringPreferencesKey

object AuthPreferenceKey {

    val ACCESS_TOKEN = stringPreferencesKey("access_token")

    val ACCESS_TIME = stringPreferencesKey("access_time")

    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

    val REFRESH_TIME = stringPreferencesKey("refresh_time")

    val ROLE = stringPreferencesKey("role")

}