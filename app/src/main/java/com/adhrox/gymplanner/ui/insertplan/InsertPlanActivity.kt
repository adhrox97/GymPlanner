package com.adhrox.gymplanner.ui.insertplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.ActivityInsertPlanBinding
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.ui.planner.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Locale
import kotlin.reflect.typeOf

@AndroidEntryPoint
class InsertPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertPlanBinding
    private val insertPlanViewModel: InsertPlanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI() {
        initListeners()
        initItems()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.btnAddExercise.setOnClickListener { addExercise() }
    }

    private fun initItems() {
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DayModel.values())
        binding.spinnerDays.adapter = arrayAdapter
    }

    private fun addExercise() {
        val exercise = binding.etExercise.text.toString()
        val day = binding.spinnerDays.selectedItem as DayModel
        val routineInfo = listOf(binding.etDuration, binding.etSets, binding.etReps, binding.etRest).map { it.text.toString() }


        if(exercise.isNotEmpty()){

            Toast.makeText(
                this,
                "Ejercicio $exercise el dia $day",
                Toast.LENGTH_LONG
            ).show()

            insertPlanViewModel.insertPlan(exercise, day, routineInfo)

            onBackPressedDispatcher.onBackPressed()

        }else{
            Toast.makeText(
                this,
                "Escriba un ejercicio",
                Toast.LENGTH_LONG
            ).show()

        }
    }
}