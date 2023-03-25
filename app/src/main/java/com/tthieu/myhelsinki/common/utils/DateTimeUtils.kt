package com.tthieu.myhelsinki.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    fun parse(dateTimeString: String): LocalDateTime = try {
        LocalDateTime.parse(dateTimeString)
    } catch (e: Exception) {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        LocalDateTime.parse(dateTimeString, dateFormatter)
    }
}