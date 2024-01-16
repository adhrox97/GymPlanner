package com.adhrox.gymplanner.domain

import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.domain.model.Plan

interface Repository {

    suspend fun getAllPlanFromDatabase(): List<Plan>

    suspend fun insertPlans(plans: List<PlanEntity>)

    suspend fun insertPlan(plan: PlanEntity)

}