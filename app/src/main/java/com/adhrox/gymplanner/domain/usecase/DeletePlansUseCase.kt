package com.adhrox.gymplanner.domain.usecase

import com.adhrox.gymplanner.domain.Repository
import javax.inject.Inject

class DeletePlansUseCase @Inject constructor(private val repository: Repository) {

    suspend fun deleteAllContentTable(){
        repository.deleteAllContentTable()
    }

    suspend fun deleteDataById(id: Long){
        repository.deleteDataById(id)
    }

    suspend fun deleteSetByPlanId(planId: Long){
        repository.deleteSetByPlanId(planId)
    }

}