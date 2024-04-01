package com.adhrox.gymplanner.data.providers

import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory.Duration
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory.Reps
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory.Rest
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory.Sets
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory.Weight
import javax.inject.Inject

class RoutineInfoCategoryProvider @Inject constructor() {
    fun getRoutineInfoCategory(): List<RoutineInfoCategory> {
        return listOf(
            Duration,
            Sets,
            Reps,
            Rest,
            Weight
        )
    }
}