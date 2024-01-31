package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.data.database.entities.toDataBase
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import javax.inject.Inject

class InsertPlanUseCase @Inject constructor(private val repository: Repository) {
    suspend fun insertAllPlans(plans: List<Plan>) {
        repository.insertPlans(plans.map { it.toDataBase() })
    }

    suspend fun insertPlan(exercise: String, day: DayModel, strRoutineInfo: List<String>) {

        val (duration, sets, reps, rest) = toNumber(strRoutineInfo)
        val newExercise = Plan(ejercicio = exercise, dia = day, duration = duration, sets = sets.toInt(), reps = reps.toInt(), rest = rest.toInt())

        repository.insertPlan(newExercise.toDataBase())
    }

    private fun toNumber(strRoutineInfo: List<String>): List<Float> {
        val number = strRoutineInfo.map {
            when {
                it.isBlank() -> 0.0f
                (it.toIntOrNull() ?: 0) <= 0 -> 0.0f
                else -> it.toFloat()
            }
        }
        return number
    }
}