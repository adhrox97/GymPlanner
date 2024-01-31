package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.R

sealed class DayInfo(var isSeleted: Boolean, val name: Int) {
    data object Monday: DayInfo(true, R.string.Monday)
    data object Tuesday: DayInfo(false, R.string.Tuesday)
    data object Wednesday: DayInfo(false, R.string.Wednesday)
    data object Thursday: DayInfo(false, R.string.Thursday)
    data object Friday: DayInfo(false, R.string.Friday)
    data object Saturday: DayInfo(false, R.string.Saturday)
    data object Sunday: DayInfo(false, R.string.Sunday)
}