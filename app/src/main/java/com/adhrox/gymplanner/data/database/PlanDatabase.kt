package com.adhrox.gymplanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adhrox.gymplanner.data.database.dao.PlanDao
import com.adhrox.gymplanner.data.database.dao.SetDao
import com.adhrox.gymplanner.data.database.entities.PlanEntity
import com.adhrox.gymplanner.data.database.entities.SetEntity

@Database(entities = [PlanEntity::class, SetEntity::class], version = 1)
abstract class PlanDatabase: RoomDatabase() {

    abstract fun getPlanDao(): PlanDao

    abstract fun getSetDao(): SetDao
}