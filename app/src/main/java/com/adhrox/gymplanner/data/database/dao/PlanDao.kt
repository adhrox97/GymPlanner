package com.adhrox.gymplanner.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adhrox.gymplanner.data.database.entities.PlanEntity
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
    fun insertPlan(plan: PlanEntity)

    @Query("SELECT * FROM plan_table WHERE id = :id")
    fun getDataById(id: Int): PlanEntity

    @Query("DELETE FROM plan_table WHERE id = :id")
    fun deleteDataById(id: Int)

    @Query("DELETE FROM plan_table")
    fun deleteAllContentTable()

}