package com.example.cuanku.helper

open class SingleEvent<out T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeendHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeendHandled) {
            null
        } else {
            hasBeendHandled = true
            content
        }
    }

}