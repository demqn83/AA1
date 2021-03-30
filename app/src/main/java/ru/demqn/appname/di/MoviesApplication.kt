package ru.demqn.appname.di

import android.app.Application

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.init(this)

    }
}