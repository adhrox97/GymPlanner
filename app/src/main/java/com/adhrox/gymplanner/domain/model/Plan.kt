package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.data.database.entities.PlanEntity

data class Plan(val id: Int = 0, val ejercicio: String, val dia: DayModel, val duration: Float, val sets: Int, val reps: Int, val rest: Int)

fun PlanEntity.toDomain() = Plan(id = id, ejercicio = ejercicio, dia = dia, duration = duration, sets = sets, reps = reps, rest = rest)