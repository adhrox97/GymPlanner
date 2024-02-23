package com.adhrox.gymplanner.ui.plandetail

import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Set

sealed class PlanDetailState {
    data object Loading : PlanDetailState()

    data class Error(val error: String) : PlanDetailState()

    data class Success(
        val id: Long,
        val exercise: String,
        val day: DayModel,
        val duration: Float,
        val sets: Int,
        val reps: Int,
        val rest: Int,
        val weight: Float,
        val set: List<Set>
    ) : PlanDetailState()
}