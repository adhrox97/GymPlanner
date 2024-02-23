package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.data.database.entities.SetEntity


data class Set(
    val setId: Long = 0,
    val planId: Long = 0,
    val isSelected: Boolean = false
)

fun SetEntity.toDomain() = Set(setId = setId, planId = planId, isSelected = isSelected)