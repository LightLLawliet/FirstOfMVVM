package com.example.firstofmvm

import android.app.Application

class HelloWorldApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(MainModel())
    }
}