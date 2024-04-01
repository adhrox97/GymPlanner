package com.adhrox.gymplanner.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.adhrox.gymplanner.domain.model.PlanWithSet

data class PlanWithSetEntity(
    @Embedded val plan: PlanEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "planId"
    )
    val sets: List<SetEntity>
)

fun PlanWithSet.toDataBase() =
    PlanWithSetEntity(plan = plan.toDataBase(), sets = sets.map { it.toDataBase() })

fun PlanWithSet.toDataBaseEdit() =
    PlanWithSetEntity(plan = plan.toDataBaseEdit(), sets = sets.map { it.toDataBase() })