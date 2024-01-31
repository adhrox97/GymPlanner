package com.adhrox.gymplanner.data.providers

import com.adhrox.gymplanner.domain.model.DayInfo
import com.adhrox.gymplanner.domain.model.DayInfo.*
import javax.inject.Inject

class DaysProvider @Inject constructor(){
    fun getDays(): List<DayInfo> {
        return listOf(
            Monday,
            Tuesday,
            Wednesday,
            Thursday,
            Friday,
            Saturday,
            Sunday
        )
    }
}