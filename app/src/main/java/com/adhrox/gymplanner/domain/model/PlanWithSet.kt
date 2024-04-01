package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.data.database.entities.PlanWithSetEntity

data class PlanWithSet(
    val plan: Plan,
    val sets: List<Set>
)

fun PlanWithSetEntity.toDomain() =
    PlanWithSet(plan = plan.toDomain(), sets = sets.map { it.toDomain() })