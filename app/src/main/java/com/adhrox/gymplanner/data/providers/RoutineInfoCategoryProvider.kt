package com.adhrox.gymplanner.data.providers

import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory.*
import javax.inject.Inject

class RoutineInfoCategoryProvider @Inject constructor() {
    fun getRoutineInfoCategory(): List<RoutineInfoCategory>{

        return listOf(
            Duration,
            Sets,
            Reps,
            Rest,
            Weight
        )

    }

}