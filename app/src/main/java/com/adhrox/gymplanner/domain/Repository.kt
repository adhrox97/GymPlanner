package com.adhrox.gymplanner.domain

import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan

interface Repository {

    suspend fun getAllPlanFromDatabase(): List<Plan>

    suspend fun insertPlans(plans: List<PlanEntity>)

    suspend fun insertPlan(plan: PlanEntity)

    suspend fun getDataByDay(day: DayModel): List<Plan>

    suspend fun getDataById(id: Int): Plan?

    suspend fun editPlan(plan: PlanEntity)

    suspend fun deleteDataById(id: Int)

    suspend fun deleteAllContentTable()
}