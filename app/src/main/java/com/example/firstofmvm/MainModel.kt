package com.example.firstofmvm

class MainModel {

    private var email: String = ""

    fun save(email: String) {
        this.email = email
    }

    fun restore() = email
}