package com.adhrox.gymplanner.ui.plandetail

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.data.providers.RoutineInfoCategoryProvider
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.domain.model.TimerModel
import com.adhrox.gymplanner.domain.usecase.DeletePlansUseCase
import com.adhrox.gymplanner.domain.usecase.EditPlanUseCase
import com.adhrox.gymplanner.domain.usecase.GetPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlanDetailViewModel @Inject constructor(private val deletePlansUseCase: DeletePlansUseCase, private val getPlansUseCase: GetPlansUseCase, private val editPlanUseCase: EditPlanUseCase, private val routineInfoCategoryProvider: RoutineInfoCategoryProvider, private val timerModel: TimerModel): ViewModel() {

    private var _state = MutableStateFlow<PlanDetailState>(PlanDetailState.Loading)
    val state: StateFlow<PlanDetailState> = _state

    private var _timer = MutableStateFlow<Long>(timerModel.getTimeLeftInMillis())
    val timer: StateFlow<Long> = _timer

    fun deleteDataById(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            deletePlansUseCase.deleteDataById(id)
        }
    }

    fun deleteAllContentTable(){
        viewModelScope.launch(Dispatchers.IO){
            deletePlansUseCase.deleteAllContentTable()
        }
    }

    fun getDataById(id: Int){
        viewModelScope.launch {
            _state.value = PlanDetailState.Loading

            val result = withContext(Dispatchers.IO){getPlansUseCase.geDataById(id)}
            if (result != null){
                _state.value = PlanDetailState.Success(result.id, result.ejercicio, result.dia, result.duration, result.sets, result.reps, result.rest, result.weight)
            }else{
                _state.value = PlanDetailState.Error("Ha sucedido un error, intente mas tarde")
            }
        }
    }

    fun editAndGet(id: Int, exercise: String, day: DayModel, strRoutineInfo: List<String>){
        viewModelScope.launch {
            val idPlan = withContext(Dispatchers.IO) { async { editPlanUseCase.editPlan(id, exercise, day, strRoutineInfo) } }
            getDataById(idPlan.await())

        }
    }

    fun getRoutineInfoCategoryList(): List<RoutineInfoCategory>{
        return routineInfoCategoryProvider.getRoutineInfoCategory()
    }

    fun setTimer(time: Long){
        timerModel.setTimeLeftInMillis(time)
        _timer.value = time
        initTimer()

    }

    private fun initTimer(){
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

    fun startTimer(){
        timerModel.startTimer()
    }

    fun pauseTimer(){
        timerModel.pauseTimer()
    }

    fun resetTimer(){
        timerModel.resetTimer()
    }

    fun resumeTimer(){
        timerModel.resumeTimer()
    }

}