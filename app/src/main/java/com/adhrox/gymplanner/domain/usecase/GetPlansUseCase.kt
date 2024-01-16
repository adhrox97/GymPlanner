package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.Plan
import javax.inject.Inject

class GetPlansUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Plan>{
        return  repository.getAllPlanFromDatabase()
    }
}