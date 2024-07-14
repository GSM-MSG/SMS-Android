package com.msg.sms.domain.extension

inline fun <reified T : Enum<T>> valueOfNull(name: String): T? {
    return try {
        enumValueOf<T>(name)
    } catch (e: IllegalArgumentException) {
        null
    }
}