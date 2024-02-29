package com.adhrox.gymplanner.domain

import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.data.database.entities.PlanWithSetEntity
import com.adhrox.gymplanner.data.database.entities.SetEntity
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet
import com.adhrox.gymplanner.domain.model.Set

interface Repository {

    suspend fun getAllPlanFromDatabase(): List<Plan>

    suspend fun getAllSets(): List<Set>

    suspend fun insertPlans(plans: List<PlanEntity>)

    suspend fun insertPlan(plan: PlanEntity)

    suspend fun insertSet(set: List<SetEntity>)

    suspend fun insertPlanWithSet(plan: PlanWithSetEntity)

    suspend fun getDataByDay(day: DayModel): List<Plan>

    suspend fun getDataById(id: Long): Plan?

    suspend fun getDataWithSetsById(id: Long): PlanWithSet?

    suspend fun getDataWithSetsByDay(day: DayModel): List<PlanWithSet>

    suspend fun updatePlan(plan: PlanEntity)

    suspend fun updatePlanWithSet(plan: PlanWithSetEntity)

    suspend fun updateSetById(isSelected: Boolean, setId: Long)

    suspend fun deleteDataById(id: Long)

    suspend fun deleteAllContentTable()

    suspend fun deleteSetByPlanId(planId: Long)
}