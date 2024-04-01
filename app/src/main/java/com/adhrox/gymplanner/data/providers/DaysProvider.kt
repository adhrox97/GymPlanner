package com.adhrox.gymplanner.data.providers

import com.adhrox.gymplanner.domain.model.DayInfo
import com.adhrox.gymplanner.domain.model.DayInfo.Friday
import com.adhrox.gymplanner.domain.model.DayInfo.Monday
import com.adhrox.gymplanner.domain.model.DayInfo.Saturday
import com.adhrox.gymplanner.domain.model.DayInfo.Sunday
import com.adhrox.gymplanner.domain.model.DayInfo.Thursday
import com.adhrox.gymplanner.domain.model.DayInfo.Tuesday
import com.adhrox.gymplanner.domain.model.DayInfo.Wednesday
import javax.inject.Inject

class DaysProvider @Inject constructor() {
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