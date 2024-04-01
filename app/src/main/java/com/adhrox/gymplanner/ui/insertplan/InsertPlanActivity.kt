package com.adhrox.gymplanner.ui.insertplan

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.ActivityInsertPlanBinding
import com.adhrox.gymplanner.domain.model.DayModel
import dagger.hilt.android.AndroidEntryPoint

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
        binding.iicvDuration.editTextInfo.text = getString(R.string.minutes).subSequence(0, 3)
        binding.iicvRest.editTextInfo.text = getString(R.string.seconds).subSequence(0, 3)

        initListeners()
        initItems()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.btnAddExercise.setOnClickListener { addExercise() }
    }

    private fun initItems() {
        val arrayAdapter = ArrayAdapter(this, R.layout.selected_item_spinner, getDayNameList())
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_item_spinner)
        binding.iicvDay.spinner.adapter = arrayAdapter
    }

    private fun addExercise() {
        val exercise = binding.etExercise.text.toString()
        val strDay = binding.iicvDay.spinner.selectedItem
        val notes = binding.etNotes.text.toString()

        val day = DayModel.values().find { getString(it.refDay) == strDay }

        val routineInfo = listOf(
            binding.iicvDuration.editText,
            binding.iicvSets.editText,
            binding.iicvReps.editText,
            binding.iicvRest.editText,
            binding.iicvWeight.editText
        ).map { it.text.toString() }

        val (strDuration, strSets, strReps, strRest, strWeight) = routineInfo


        if (exercise.isNotEmpty()) {
            insertPlanViewModel.insertPlanWithSet(
                exercise,
                day!!,
                strDuration,
                strSets,
                strReps,
                strRest,
                strWeight,
                notes
            )
            onBackPressedDispatcher.onBackPressed()
        } else {
            Toast.makeText(
                this,
                R.string.hint_write_exercise,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getDayNameList(): List<String> {
        val dayNameList = DayModel.values().map {
            getString(it.refDay)
        }
        return dayNameList
    }
}