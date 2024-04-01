package com.adhrox.gymplanner.ui.plandetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhrox.gymplanner.data.providers.RoutineInfoCategoryProvider
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.domain.model.Set
import com.adhrox.gymplanner.domain.model.TimerModel
import com.adhrox.gymplanner.domain.usecase.DeletePlansUseCase
import com.adhrox.gymplanner.domain.usecase.EditPlanUseCase
import com.adhrox.gymplanner.domain.usecase.GetPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlanDetailViewModel @Inject constructor(
    private val deletePlansUseCase: DeletePlansUseCase,
    private val getPlansUseCase: GetPlansUseCase,
    private val editPlanUseCase: EditPlanUseCase,
    private val routineInfoCategoryProvider: RoutineInfoCategoryProvider,
    private val timerModel: TimerModel
) : ViewModel() {

    private var _state = MutableStateFlow<PlanDetailState>(PlanDetailState.Loading)
    val state: StateFlow<PlanDetailState> = _state

    private var _timer = MutableStateFlow<Long>(timerModel.getTimeLeftInMillis())
    val timer: StateFlow<Long> = _timer

    private var _sets = MutableStateFlow<List<Set>>(emptyList())
    val sets: StateFlow<List<Set>> = _sets

    fun deleteDataById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePlansUseCase.deleteDataById(id)
        }
    }

    fun deleteAllContentTable() {
        viewModelScope.launch(Dispatchers.IO) {
            deletePlansUseCase.deleteAllContentTable()
        }
    }

    fun getAllSets() {
        viewModelScope.launch {
            val sets = withContext(Dispatchers.IO) { getPlansUseCase.getAllSets() }
            _sets.value = sets
        }
    }

    fun getDataWithSetById(id: Long) {
        viewModelScope.launch {
            _state.value = PlanDetailState.Loading

            val result = withContext(Dispatchers.IO) { getPlansUseCase.getDataWithSetsById(id) }
            if (result != null) {
                _state.value = PlanDetailState.Success(
                    result.plan.id,
                    result.plan.exercise,
                    result.plan.day,
                    result.plan.duration,
                    result.plan.sets,
                    result.plan.reps,
                    result.plan.rest,
                    result.plan.weight,
                    result.sets,
                    result.plan.notes
                )
            } else {
                _state.value = PlanDetailState.Error("Ha sucedido un error, intente mas tarde")
            }
        }
    }

    fun updatePlanAndGet(
        id: Long,
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String,
        notes: String,
        currentSets: Int
    ) {
        viewModelScope.launch {

            val idPlan = withContext(Dispatchers.IO) {
                async {
                    if ((strSets.toIntOrNull() ?: 0) == currentSets) {
                        editPlanUseCase.updatePlan(
                            id,
                            exercise,
                            day,
                            strDuration,
                            strSets,
                            strReps,
                            strRest,
                            strWeight,
                            notes
                        )
                    } else {
                        editPlanUseCase.updatePlanWithSet(
                            id,
                            exercise,
                            day,
                            strDuration,
                            strSets,
                            strReps,
                            strRest,
                            strWeight,
                            notes
                        )
                    }
                }
            }
            getDataWithSetById(idPlan.await())
        }
    }

    fun updateSetById(isSelected: Boolean, setId: Long, planId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            editPlanUseCase.updateSetById(isSelected, setId)
            getDataWithSetById(planId)
        }
    }

    fun getRoutineInfoCategoryList(): List<RoutineInfoCategory> {
        return routineInfoCategoryProvider.getRoutineInfoCategory()
    }

    fun setTimer(time: Long) {
        timerModel.setTimeLeftInMillis(time)
        _timer.value = time
        initTimer()
    }

    private fun initTimer() {
        timerModel.listener = object : TimerModel.TimerListener {
            override fun onTimerTick(timeLeftInMillis: Long) {
                _timer.value = timeLeftInMillis
            }

            override fun onTimerFinish() {
                _timer.value = timerModel.getTimeLeftInMillis()
                resetTimer()
            }

            override fun onTimerReset() {
                _timer.value = timerModel.getTimeLeftInMillis()
            }
        }
    }

    fun startTimer() {
        timerModel.startTimer()
    }

    fun pauseTimer() {
        timerModel.pauseTimer()
    }

    fun resetTimer() {
        timerModel.resetTimer()
    }

    fun resumeTimer() {
        timerModel.resumeTimer()
    }

    fun getTimerStatus(): Boolean {
        return timerModel.getTimerStatus()
    }

}