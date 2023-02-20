package com.example.firstofmvm

import android.util.Patterns

class MainViewModel(private val model: MainModel) : Observe {

    fun login(email: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.callback.postSuccess()
        } else {
            this.callback.postError("wrong email")
        }
    }

    fun clearEmail(input: String) {
        model.save(input)
        callback.postSuccess()
    }

    private var callback: UiStateCallback = UiStateCallback.Empty()

    override fun observe(callback: UiStateCallback) {
        this.callback = callback
    }


    fun clear() {
        callback = UiStateCallback.Empty()
    }

    fun init(firstTime: Boolean) {
        if (!firstTime) {
            this.callback.postEmail(model.restore())
        }
    }

}

interface Observe {
    fun observe(callback: UiStateCallback)
}

interface UiStateCallback {

    fun postEmail(value: String)

    fun postSuccess()

    fun postError(message: String)

    class Empty : UiStateCallback {
        override fun postEmail(value: String) = Unit

        override fun postSuccess() = Unit

        override fun postError(message: String) = Unit
    }
}