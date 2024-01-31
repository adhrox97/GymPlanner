package com.adhrox.gymplanner.data

import android.util.Log
import com.adhrox.gymplanner.data.database.dao.PlanDao
import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.toDomain
import javax.inject.Inject

class PlanRepository @Inject constructor(private val planDao: PlanDao): Repository {
    override suspend fun getAllPlanFromDatabase(): List<Plan>{
        val response = planDao.getAllPlans()
        return response.map { it.toDomain() }
    }

    override suspend fun getDataByDay(day: DayModel): List<Plan>{
        val response = planDao.getDataByDay(day)
        return response.map { it.toDomain() }
    }

    override suspend fun insertPlans(plans: List<PlanEntity>){
        planDao.insertAllPlans(plans)
    }

    override suspend fun insertPlan(plan: PlanEntity) {
        planDao.insertPlan(plan)
    }

    override suspend fun getDataById(id: Int): Plan? {
        runCatching { planDao.getDataById(id) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("adhrox", "Error ${it.message}") }
        return null
    }

    override suspend fun deleteDataById(id: Int) {
        planDao.deleteDataById(id)
    }

    override suspend fun deleteAllContentTable() {
        planDao.deleteAllContentTable()
    }
}