package com.example.parliamentapp.util

import java.text.SimpleDateFormat
import java.util.*

enum class Status {
    LOADING,
    DONE,
    ERROR
}

fun convertLongToDateString(systemTime: Long)
    = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault()).format(systemTime).toString()
