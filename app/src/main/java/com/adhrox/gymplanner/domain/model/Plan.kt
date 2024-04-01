package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.data.database.entities.PlanEntity

data class Plan(
    val id: Long = 0,
    val exercise: String,
    val day: DayModel,
    val duration: Float,
    val sets: Int,
    val reps: Int,
    val rest: Int,
    val weight: Float,
    val notes: String
)

fun PlanEntity.toDomain() = Plan(
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