package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.data.database.entities.toDataBaseEdit
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.utils.ConversionUtils
import javax.inject.Inject

class EditPlanUseCase @Inject constructor(private val repository: Repository) {

    suspend fun editPlan(id: Int, exercise: String, day: DayModel, strRoutineInfo: List<String>): Int {

        val (duration, sets, reps, rest, weight) = ConversionUtils.listStringToListFloat(strRoutineInfo)

        val editPlan = Plan(id, exercise, day, duration, sets.toInt(), reps.toInt(), rest.toInt(), weight)

        repository.editPlan(editPlan.toDataBaseEdit())

        return id

    }

}