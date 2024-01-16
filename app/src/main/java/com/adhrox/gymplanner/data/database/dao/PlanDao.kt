package com.adhrox.gymplanner.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adhrox.gymplanner.data.database.entities.PlanEntity

@Dao
interface PlanDao {

    @Query("SELECT * FROM plan_table")
    fun getAllPlans(): List<PlanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlans(plans: List<PlanEntity>)

    @Insert
    fun insertPlan(plan: PlanEntity)

}