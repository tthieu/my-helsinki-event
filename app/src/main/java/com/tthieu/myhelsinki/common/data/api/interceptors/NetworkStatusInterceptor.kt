package com.tthieu.myhelsinki.common.data.api.interceptors

import com.tthieu.myhelsinki.common.data.api.ConnectionManager
import com.tthieu.myhelsinki.common.domain.model.NetworkUnavailableException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkStatusInterceptor @Inject constructor(private val connectionManager: ConnectionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}