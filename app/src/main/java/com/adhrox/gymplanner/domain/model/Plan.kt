package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.data.database.entities.PlanEntity

data class Plan (val id: Int, val ejercicio: String, val dia: DayModel)

fun PlanEntity.toDomain() = Plan(id, ejercicio, dia)