package com.adhrox.gymplanner.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.adhrox.gymplanner.domain.model.Set

@Entity(
    tableName = "set_table", foreignKeys = [
        ForeignKey(
            entity = PlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class SetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "setId") val setId: Long = 0,
    @ColumnInfo(name = "planId") var planId: Long = 0,
    @ColumnInfo(name = "isSelected") val isSelected: Boolean
)

fun Set.toDataBase() = SetEntity(isSelected = isSelected)