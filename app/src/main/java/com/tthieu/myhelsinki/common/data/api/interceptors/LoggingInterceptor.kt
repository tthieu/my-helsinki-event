package com.tthieu.myhelsinki.common.data.api.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import com.tthieu.myhelsinki.logging.Logger
import javax.inject.Inject

class LoggingInterceptor @Inject constructor(): HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Logger.i(message)
    }
}