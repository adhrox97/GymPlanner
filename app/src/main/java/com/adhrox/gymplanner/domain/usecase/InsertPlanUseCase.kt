package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.data.database.entities.toDataBase
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import javax.inject.Inject

class InsertPlanUseCase @Inject constructor(private val repository: Repository) {
    suspend fun insertAllPlans(plans: List<Plan>){
        repository.insertPlans(plans.map { it.toDataBase() })
    }
    suspend fun insertPlan(exercise: String, day: DayModel){
        val newExercise = Plan(exercise, day)
        repository.insertPlan(newExercise.toDataBase())
    }
}