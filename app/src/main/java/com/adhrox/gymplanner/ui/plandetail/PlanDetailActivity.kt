package com.adhrox.gymplanner.ui.plandetail

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.ActivityPlanDetailBinding
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.domain.model.Set
import com.adhrox.gymplanner.ui.plandetail.adapters.SetAdapter
import com.adhrox.gymplanner.ui.plandetail.adapters.RoutineInfoCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanDetailBinding
    private lateinit var routineInfoCategoryAdapter: RoutineInfoCategoryAdapter
    private lateinit var setAdapter: SetAdapter
    private var currentPlan: PlanDetailState.Success? = null
    private val planDetailViewModel: PlanDetailViewModel by viewModels()
    private val args: PlanDetailActivityArgs by navArgs()
    private var enableEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        planDetailViewModel.getDataWithSetById(args.idPlan)
        planDetailViewModel.getAllSets()
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.btnDeletePlans.setOnClickListener {
            planDetailViewModel.deleteAllContentTable()
        }
        binding.fabDeleteById.setOnClickListener { showDialogDeletePlanById() }
        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.ivEdit.setOnClickListener { enableEdit() }
        binding.btnEditPlan.setOnClickListener { editPlan() }

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                planDetailViewModel.state.collect(){

                    when(it){
                        is PlanDetailState.Error -> errorState(it)
                        PlanDetailState.Loading -> loadingState()
                        is PlanDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(stateSuccess: PlanDetailState.Success) {

        currentPlan = stateSuccess

        binding.pbPlanDetail.isVisible = false
        binding.tvError.isVisible = false

        binding.tvId.text = stateSuccess.id.toString()
        binding.tvExerciseName.text = stateSuccess.exercise

        initList(stateSuccess)
    }

    private fun loadingState() {
        binding.pbPlanDetail.isVisible = true
        binding.tvError.isVisible = false
    }

    private fun errorState(stateError: PlanDetailState.Error) {
        binding.pbPlanDetail.isVisible = false
        binding.tvError.isVisible = true
        binding.tvError.text = stateError.error
    }

    private fun initList(planDetail: PlanDetailState.Success){
        routineInfoCategoryAdapter = RoutineInfoCategoryAdapter(planDetailViewModel.getRoutineInfoCategoryList(), planDetail) { onItemSelected(it, planDetail) }
        binding.rvRoutineInfo.apply {
            layoutManager = GridLayoutManager(context, routineInfoCategoryAdapter.itemCount)
            adapter = routineInfoCategoryAdapter
        }

        setAdapter = SetAdapter(planDetail.set) { onSetSelected(it) }
        binding.rvSet.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = setAdapter
        }

    }

    private fun onItemSelected(infoSelected: RoutineInfoCategory, planDetail: PlanDetailState.Success) {

        when(infoSelected){
            RoutineInfoCategory.Duration -> {
                val time = (planDetail.duration*60000).toLong()
                showTimerCountdownDialog(time)
            }
            RoutineInfoCategory.Rest -> {
                val time = (planDetail.rest*1000).toLong()
                showTimerCountdownDialog(time)
            }
            RoutineInfoCategory.Reps -> {}
            RoutineInfoCategory.Sets -> {}
            RoutineInfoCategory.Weight -> {}
        }

    }

    private fun onSetSelected(set: Set) {

        planDetailViewModel.updateSetById(!set.isSelected, set.setId, set.planId)

    }

    private fun editPlan() {
        if(currentPlan != null) {

            val plan = currentPlan as PlanDetailState.Success

            val recyclerViewRoutineInfo = binding.rvRoutineInfo
            val data = mutableListOf<String>()

            for (i in 0 until recyclerViewRoutineInfo.childCount) {
                val view = recyclerViewRoutineInfo.getChildAt(i)
                val editText = view.findViewById<EditText>(R.id.etRoutineInfoData)
                val dataText = editText.text.toString()
                data.add(dataText)
            }

            val (strDuration, strSets, strReps, strRest, strWeight) = data

            planDetailViewModel.updatePlanAndGet(plan.id, plan.exercise, plan.day, strDuration, strSets, strReps, strRest, strWeight, plan.sets)
            //setAdapter.updateList()
            //routineInfoCategoryAdapter.updateList()
            enableEdit()
        }
    }

    private fun enableEdit() {
        enableEdit = !enableEdit
        binding.btnEditPlan.isEnabled = enableEdit
        routineInfoCategoryAdapter.enableEditing(enableEdit)
    }

    private fun showTimerCountdownDialog(timeCountdown: Long) {
        val dialog = DialogCountdownFragment(timeCountdown)
        dialog.show(supportFragmentManager, "dialogTimeCountdown")
    }

    private fun deleteDataById() {
        planDetailViewModel.deleteDataById(args.idPlan)
        onBackPressedDispatcher.onBackPressed()
    }

    private fun showDialogDeletePlanById(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_alert)

        val btnPositive: Button = dialog.findViewById(R.id.btnPositive)
        val btnNegative: Button = dialog.findViewById(R.id.btnNegative)

        btnPositive.setOnClickListener {
            deleteDataById()
            dialog.dismiss()
        }

        btnNegative.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}