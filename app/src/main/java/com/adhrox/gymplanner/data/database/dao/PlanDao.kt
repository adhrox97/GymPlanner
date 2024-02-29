package com.adhrox.gymplanner.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.data.database.entities.PlanWithSetEntity
import com.adhrox.gymplanner.data.database.entities.SetEntity
import com.adhrox.gymplanner.domain.model.DayModel

@Dao
interface PlanDao {

    @Query("SELECT * FROM plan_table")
    fun getAllPlans(): List<PlanEntity>

    @Query("SELECT * FROM plan_table where dia = :day")
    fun getDataByDay(day: DayModel): List<PlanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlans(plans: List<PlanEntity>)

    @Insert
    fun insertPlan(plan: PlanEntity): Long

    @Insert
    fun insertSet(set: List<SetEntity>)

    @Transaction
    fun insertPlanWithSet(plan: PlanEntity, sets: List<SetEntity>){
        val planId = insertPlan(plan)
        sets.forEach { it.planId = planId }
        insertSet(sets)
    }

    @Query("SELECT * FROM plan_table WHERE id = :id")
    fun getDataById(id: Long): PlanEntity

    @Transaction
    @Query("SELECT * FROM plan_table where id = :id")
    fun getDataWithSetsById(id: Long): PlanWithSetEntity

    @Transaction
    @Query("SELECT * FROM plan_table where dia = :day")
    fun getDataWithSetsByDay(day: DayModel): List<PlanWithSetEntity>

    @Update
    fun updatePlan(plan: PlanEntity)

    @Transaction
    fun updatePlanWithSet(plan: PlanEntity, sets: List<SetEntity>){
        updatePlan(plan)
        sets.forEach { it.planId = plan.id }
        insertSet(sets)
    }

    @Query("DELETE FROM plan_table WHERE id = :id")
    fun deleteDataById(id: Long)

    @Query("DELETE FROM plan_table")
    fun deleteAllContentTable()

}