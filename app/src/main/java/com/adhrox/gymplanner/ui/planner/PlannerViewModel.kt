package com.adhrox.gymplanner.ui.planner

import androidx.lifecycle.ViewModel
import com.adhrox.gymplanner.data.providers.DaysProvider
import com.adhrox.gymplanner.domain.model.DayInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(daysProvider: DaysProvider): ViewModel() {
    private  var _days = MutableStateFlow<List<DayInfo>>(emptyList())
    val days:StateFlow<List<DayInfo>> = _days

    init {
        _days.value = daysProvider.getDays()
    }
}