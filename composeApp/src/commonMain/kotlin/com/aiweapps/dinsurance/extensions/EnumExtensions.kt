package com.aiweapps.dinsurance.extensions

import kotlin.reflect.KClass

inline fun <reified T : Enum<T>> KClass<out T>.enumValues(): Array<T> {
    return enumValuesKotlin()
}

inline fun <reified T : Enum<T>> enumValuesKotlin(): Array<T> {
    return enumValues<T>()
}
