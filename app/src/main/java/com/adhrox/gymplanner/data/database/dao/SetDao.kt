package com.adhrox.gymplanner.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adhrox.gymplanner.data.database.entities.SetEntity
import com.adhrox.gymplanner.domain.model.DayModel

@Dao
interface SetDao {

    @Insert
    fun insertSet(set: List<SetEntity>)

    @Query("SELECT * FROM set_table")
    fun getAllSets(): List<SetEntity>

    @Query("DELETE FROM set_table WHERE planId = :planId")
    fun deleteSetByPlanId(planId: Long)

    @Query("UPDATE set_table SET isSelected = :isSelected WHERE setId = :setId")
    fun updateSetById(isSelected: Boolean, setId: Long)

    @Query("UPDATE set_table SET isSelected = 0 WHERE planId IN (SELECT id FROM plan_table WHERE day = :day)")
    fun resetSetsByDay(day: DayModel)


}