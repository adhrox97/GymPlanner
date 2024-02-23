package com.adhrox.gymplanner.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan

@Entity(tableName = "plan_table")
data class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "ejercicio") val ejercicio: String,
    @ColumnInfo(name = "dia") val dia: DayModel,
    @ColumnInfo(name = "duration") val duration: Float,
    @ColumnInfo(name = "sets") val sets: Int,
    @ColumnInfo(name = "reps") val reps: Int,
    @ColumnInfo(name = "rest") val rest: Int,
    @ColumnInfo(name = "weight") val weight: Float
)

fun Plan.toDataBase() = PlanEntity(
    ejercicio = ejercicio,
    dia = dia,
    duration = duration,
    sets = sets,
    reps = reps,
    rest = rest,
    weight = weight
)

fun Plan.toDataBaseEdit() = PlanEntity(
    id = id,
    ejercicio = ejercicio,
    dia = dia,
    duration = duration,
    sets = sets,
    reps = reps,
    rest = rest,
    weight = weight
)