package com.adhrox.gymplanner.domain.model

import com.adhrox.gymplanner.R

sealed class DayInfo(var isSeleted: Boolean, val name: Int) {
    data object Lunes: DayInfo(true, R.string.Lunes)
    data object Martes: DayInfo(false, R.string.Martes)
    data object Miercoles: DayInfo(false, R.string.Miercoles)
    data object Jueves: DayInfo(false, R.string.Jueves)
    data object Viernes: DayInfo(false, R.string.Viernes)
    data object Sabado: DayInfo(false, R.string.Sabado)
    data object Domingo: DayInfo(false, R.string.Domingo)
}