package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.data.database.entities.toDataBase
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet
import com.adhrox.gymplanner.utils.ConversionUtils
import com.adhrox.gymplanner.utils.GeneratorUtils
import javax.inject.Inject

class InsertPlanUseCase @Inject constructor(private val repository: Repository) {
    suspend fun insertAllPlans(plans: List<Plan>) {
        repository.insertPlans(plans.map { it.toDataBase() })
    }

    suspend fun insertPlan(
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String,
        notes: String
    ) {
        val (duration, sets, reps, rest, weight) = ConversionUtils.stringsToListFloat(
            strDuration,
            strSets,
            strReps,
            strRest,
            strWeight
        )
        val newExercise = Plan(
            exercise = exercise,
            day = day,
            duration = duration,
            sets = sets.toInt(),
            reps = reps.toInt(),
            rest = rest.toInt(),
            weight = weight,
            notes = notes
        )

        repository.insertPlan(newExercise.toDataBase())
    }

    suspend fun insertPlanWithSet(
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String,
        notes: String
    ) {
        val (duration, sets, reps, rest, weight) = ConversionUtils.stringsToListFloat(
            strDuration,
            strSets,
            strReps,
            strRest,
            strWeight
        )
        val newExercise = Plan(
            exercise = exercise,
            day = day,
            duration = duration,
            sets = sets.toInt(),
            reps = reps.toInt(),
            rest = rest.toInt(),
            weight = weight,
            notes = notes
        )

        val planWithSet = PlanWithSet(newExercise, GeneratorUtils.generateSets(sets.toInt()))

        repository.insertPlanWithSet(planWithSet.toDataBase())
    }
}