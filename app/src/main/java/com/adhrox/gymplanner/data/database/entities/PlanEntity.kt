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
    @ColumnInfo(name = "exercise") val exercise: String,
    @ColumnInfo(name = "day") val day: DayModel,
    @ColumnInfo(name = "duration") val duration: Float,
    @ColumnInfo(name = "sets") val sets: Int,
    @ColumnInfo(name = "reps") val reps: Int,
    @ColumnInfo(name = "rest") val rest: Int,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "notes") val notes: String
)

fun Plan.toDataBase() = PlanEntity(
    exercise = exercise,
    day = day,
    duration = duration,
    sets = sets,
    reps = reps,
    rest = rest,
    weight = weight,
    notes = notes
)

fun Plan.toDataBaseEdit() = PlanEntity(
    id = id,
    exercise = exercise,
    day = day,
    duration = duration,
    sets = sets,
    reps = reps,
    rest = rest,
    weight = weight,
    notes = notes
)