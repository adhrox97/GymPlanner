package com.adhrox.gymplanner.ui.insertplan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.usecase.InsertPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertPlanViewModel @Inject constructor(private val insertPlanUseCase: InsertPlanUseCase) :
    ViewModel() {

    fun insertPlan(
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String,
        notes: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            insertPlanUseCase.insertPlan(
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

    fun insertPlanWithSet(
        exercise: String,
        day: DayModel,
        strDuration: String,
        strSets: String,
        strReps: String,
        strRest: String,
        strWeight: String,
        notes: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            insertPlanUseCase.insertPlanWithSet(
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