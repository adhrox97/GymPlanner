package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet
import com.adhrox.gymplanner.domain.model.Set
import javax.inject.Inject

class GetPlansUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getAllPlan(): List<Plan>{
        return  repository.getAllPlanFromDatabase()
    }

    suspend fun getAllSets(): List<Set>{
        return repository.getAllSets()
    }

    suspend fun getDataByDay(day: DayModel): List<Plan>{
        return repository.getDataByDay(day)
    }

    suspend fun getDataWithSetsByDay(day: DayModel): List<PlanWithSet>{
        return repository.getDataWithSetsByDay(day)
    }

    suspend fun geDataById(id: Long): Plan?{
        return repository.getDataById(id)
    }

    suspend fun getDataWithSetsById(id: Long): PlanWithSet? {
        return repository.getDataWithSetsById(id)
    }
}