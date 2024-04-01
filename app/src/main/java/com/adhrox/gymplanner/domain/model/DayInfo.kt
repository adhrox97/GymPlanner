package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.R

sealed class DayInfo(val name: Int) {
    data object Monday: DayInfo(R.string.monday)
    data object Tuesday: DayInfo(R.string.tuesday)
    data object Wednesday: DayInfo(R.string.wednesday)
    data object Thursday: DayInfo(R.string.thursday)
    data object Friday: DayInfo(R.string.friday)
    data object Saturday: DayInfo(R.string.saturday)
    data object Sunday: DayInfo(R.string.sunday)
}