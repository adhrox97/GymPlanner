package com.adhrox.gymplanner.domain.usecase

import android.util.Log
import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.data.database.entities.SetEntity
import com.adhrox.gymplanner.data.database.entities.toDataBase
import com.adhrox.gymplanner.data.database.entities.toDataBaseEdit
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet
import com.adhrox.gymplanner.utils.ConversionUtils
import com.adhrox.gymplanner.utils.GeneratorUtils
import javax.inject.Inject

class EditPlanUseCase @Inject constructor(private val repository: Repository) {

    suspend fun updatePlan(
        id: Long,
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String
    ): Long {

        val (duration, sets, reps, rest, weight) = ConversionUtils.stringsToListFloat(
            strDuration,
            strSets,
            strReps,
            strRest,
            strWeight
        )
        val editPlan =
            Plan(id, exercise, day, duration, sets.toInt(), reps.toInt(), rest.toInt(), weight)

        repository.updatePlan(editPlan.toDataBaseEdit())

        return id
    }

    suspend fun updatePlanWithSet(
        id: Long,
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String
    ): Long {

        val (duration, sets, reps, rest, weight) = ConversionUtils.stringsToListFloat(
            strDuration,
            strSets,
            strReps,
            strRest,
            strWeight
        )
        val editPlan =
            Plan(id, exercise, day, duration, sets.toInt(), reps.toInt(), rest.toInt(), weight)

        val generatedSets = GeneratorUtils.generateSets(sets.toInt(), id)

        val planWithSet = PlanWithSet(editPlan, generatedSets)

        repository.deleteSetByPlanId(id)

        repository.updatePlanWithSet(planWithSet.toDataBaseEdit())

        return id

    }

    suspend fun updateSetById(isSelected: Boolean, setId: Long) {
        repository.updateSetById(isSelected, setId)
    }

}