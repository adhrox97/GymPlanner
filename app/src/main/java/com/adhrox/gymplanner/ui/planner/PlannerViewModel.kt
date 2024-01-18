package com.adhrox.gymplanner.ui.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhrox.gymplanner.data.providers.DaysProvider
import com.adhrox.gymplanner.domain.model.DayInfo
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.usecase.GetPlansUseCase
import com.adhrox.gymplanner.domain.usecase.InsertPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(daysProvider: DaysProvider, private val getPlansUseCase: GetPlansUseCase): ViewModel() {
    private  var _days = MutableStateFlow<List<DayInfo>>(emptyList())
    val days:StateFlow<List<DayInfo>> = _days

    private var _exercises = MutableStateFlow<List<Plan>>(emptyList())
    private val exercises: StateFlow<List<Plan>> = _exercises

    init {
        _days.value = daysProvider.getDays()
    }
    fun getAllPlans(): StateFlow<List<Plan>>{
        viewModelScope.launch {
            val plans = withContext(Dispatchers.IO){getPlansUseCase()}
            _exercises.value = plans
        }
        return exercises
    }
}