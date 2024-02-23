package com.adhrox.gymplanner.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.adhrox.gymplanner.data.database.entities.SetEntity

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

}