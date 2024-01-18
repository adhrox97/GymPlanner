package com.adhrox.gymplanner.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan

@Entity(tableName = "plan_table")
data class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "ejercicio") val ejercicio: String,
    @ColumnInfo(name = "dia") val dia: DayModel
)

fun Plan.toDataBase()= PlanEntity(ejercicio = ejercicio, dia = dia)