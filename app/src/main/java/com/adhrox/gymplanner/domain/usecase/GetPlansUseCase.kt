package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import javax.inject.Inject

class GetPlansUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getAllPlan(): List<Plan>{
        return  repository.getAllPlanFromDatabase()
    }

    suspend fun getDataByDay(day: DayModel): List<Plan>{
        return repository.getDataByDay(day)
    }

    suspend fun geDataById(id: Int): Plan?{
        return repository.getDataById(id)
    }
}