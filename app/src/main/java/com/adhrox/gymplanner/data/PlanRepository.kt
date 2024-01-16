package com.adhrox.gymplanner.data

import com.adhrox.gymplanner.data.database.dao.PlanDao
import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.toDomain
import javax.inject.Inject

class PlanRepository @Inject constructor(private val planDao: PlanDao): Repository {
    override suspend fun getAllPlanFromDatabase(): List<Plan>{
        val response = planDao.getAllPlans()
        return response.map { it.toDomain() }
    }

    override suspend fun insertPlans(plans: List<PlanEntity>){
        planDao.insertAllPlans(plans)
    }

    override suspend fun insertPlan(plan: PlanEntity) {
        planDao.insertPlan(plan)
    }
}