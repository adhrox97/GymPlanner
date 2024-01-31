package com.adhrox.gymplanner.ui.plandetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhrox.gymplanner.databinding.ActivityPlanDetailBinding
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.ui.plandetail.adapters.RoutineInfoCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanDetailBinding
    private lateinit var routineInfoCategoryAdapter: RoutineInfoCategoryAdapter
    private val planDetailViewModel: PlanDetailViewModel by viewModels()
    private val args: PlanDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        planDetailViewModel.getDataById(args.idPlan)
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
        binding.ivDeleteById.setOnClickListener {
            planDetailViewModel.deleteDataById(args.idPlan)
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
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
        binding.pbPlanDetail.isVisible = false
        binding.tvError.isVisible = false

        binding.tvId.text = args.idPlan.toString()
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
        routineInfoCategoryAdapter = RoutineInfoCategoryAdapter(planDetailViewModel.getRoutineInfoCategoryList(), planDetail){onItemSelected(it)}
        binding.rvRoutineInfo.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = routineInfoCategoryAdapter
        }

    }

    private fun onItemSelected(infoSelected: RoutineInfoCategory) {

    }

}