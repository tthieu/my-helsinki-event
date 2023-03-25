package com.tthieu.myhelsinki

import android.app.Application
import com.tthieu.myhelsinki.logging.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventSearchApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }
}