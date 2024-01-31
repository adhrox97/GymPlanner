package com.adhrox.gymplanner.ui.plandetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhrox.gymplanner.data.providers.RoutineInfoCategoryProvider
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.domain.usecase.DeletePlansUseCase
import com.adhrox.gymplanner.domain.usecase.GetPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlanDetailViewModel @Inject constructor(private val deletePlansUseCase: DeletePlansUseCase, private val getPlansUseCase: GetPlansUseCase, private val routineInfoCategoryProvider: RoutineInfoCategoryProvider): ViewModel() {

    private var _state = MutableStateFlow<PlanDetailState>(PlanDetailState.Loading)
    val state: StateFlow<PlanDetailState> = _state

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
        viewModelScope.launch{
            _state.value = PlanDetailState.Loading
            val result = withContext(Dispatchers.IO){ getPlansUseCase.geDataById(id) }
            if (result != null){
                _state.value = PlanDetailState.Success(result.id, result.ejercicio, result.dia, result.duration, result.sets, result.reps, result.rest)
            }else{
                _state.value = PlanDetailState.Error("Ha sucedido un error, intente mas tarde")
            }
        }
    }

    fun getRoutineInfoCategoryList(): List<RoutineInfoCategory>{
        return routineInfoCategoryProvider.getRoutineInfoCategory()
    }

}