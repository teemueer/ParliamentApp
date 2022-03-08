/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Helper functions
 */

package com.example.parliamentapp.util

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToDateString(systemTime: Long)
    = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault()).format(systemTime).toString()
