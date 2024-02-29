package com.adhrox.gymplanner.data

import android.util.Log
import com.adhrox.gymplanner.data.database.dao.PlanDao
import com.adhrox.gymplanner.data.database.dao.SetDao
import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.data.database.entities.PlanWithSetEntity
import com.adhrox.gymplanner.data.database.entities.SetEntity
import com.adhrox.gymplanner.domain.Repository
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet
import com.adhrox.gymplanner.domain.model.Set
import com.adhrox.gymplanner.domain.model.toDomain
import javax.inject.Inject

class PlanRepository @Inject constructor(private val planDao: PlanDao, private val setDao: SetDao): Repository {
    override suspend fun getAllPlanFromDatabase(): List<Plan>{
        val response = planDao.getAllPlans()
        return response.map { it.toDomain() }
    }

    override suspend fun getAllSets(): List<Set>{
        val response = setDao.getAllSets()
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

    override suspend fun insertSet(set: List<SetEntity>) {
        setDao.insertSet(set)
    }

    override suspend fun insertPlanWithSet(plan: PlanWithSetEntity) {
        planDao.insertPlanWithSet(plan.plan, plan.sets)
    }

    override suspend fun getDataById(id: Long): Plan? {
        runCatching { planDao.getDataById(id) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("adhrox", "Error ${it.message}") }
        return null
    }

    override suspend fun getDataWithSetsById(id: Long): PlanWithSet? {
        runCatching { planDao.getDataWithSetsById(id) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("adhrox", "Error ${it.message}") }
        return null
    }

    override suspend fun getDataWithSetsByDay(day: DayModel): List<PlanWithSet> {
        val response = planDao.getDataWithSetsByDay(day)
        return response.map { it.toDomain() }
    }

    override suspend fun updatePlan(plan: PlanEntity) {
        planDao.updatePlan(plan)
    }

    override suspend fun updatePlanWithSet(plan: PlanWithSetEntity) {
        planDao.updatePlanWithSet(plan.plan, plan.sets)
    }

    override suspend fun updateSetById(isSelected: Boolean, setId: Long){
        setDao.updateSetById(isSelected, setId)
    }

    override suspend fun deleteDataById(id: Long) {
        planDao.deleteDataById(id)
    }

    override suspend fun deleteAllContentTable() {
        planDao.deleteAllContentTable()
    }

    override suspend fun deleteSetByPlanId(planId: Long) {
        setDao.deleteSetByPlanId(planId)
    }
}