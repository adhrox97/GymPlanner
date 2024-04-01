package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.R

sealed class RoutineInfoCategory(var icon: Int, var infoName: Int) {
    data object Duration : RoutineInfoCategory(R.drawable.ic_clock, R.string.duration)
    data object Sets : RoutineInfoCategory(R.drawable.ic_sets, R.string.sets)
    data object Reps : RoutineInfoCategory(R.drawable.ic_reps, R.string.reps)
    data object Rest : RoutineInfoCategory(R.drawable.ic_rest, R.string.rest)
    data object Weight : RoutineInfoCategory(R.drawable.ic_weight, R.string.weight)
}