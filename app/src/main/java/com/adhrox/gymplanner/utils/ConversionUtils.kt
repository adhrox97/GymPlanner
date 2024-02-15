package com.adhrox.gymplanner.utils

import android.util.Log

object ConversionUtils {
     fun listStringToListFloat(strRoutineInfo: List<String>): List<Float> {
        val numbers = strRoutineInfo.map {
            when {
                it.isBlank() -> 0.0f
                (it.toFloatOrNull() ?: 0.0f) <= 0.0f -> 0.0f
                else -> it.toFloat()
            }
        }
        return numbers
    }
}