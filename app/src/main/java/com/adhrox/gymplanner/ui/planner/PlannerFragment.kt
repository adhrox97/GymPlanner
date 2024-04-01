package com.adhrox.gymplanner.ui.planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.FragmentPlannerBinding
import com.adhrox.gymplanner.domain.model.DayInfo
import com.adhrox.gymplanner.domain.model.DayModel
import com.adhrox.gymplanner.domain.model.PlanWithSet
import com.adhrox.gymplanner.ui.planner.adapters.PlanAdapter
import com.adhrox.gymplanner.ui.planner.adapters.PlannerDayAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class PlannerFragment : Fragment() {

    private val plannerViewModel by viewModels<PlannerViewModel>()
    private lateinit var plannerDayAdapter: PlannerDayAdapter
    private lateinit var planAdapter: PlanAdapter
    private lateinit var currentlySelectedDay: DayModel

    private var planList: List<PlanWithSet> = emptyList()

    private var _binding: FragmentPlannerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plannerViewModel.getDataWithSetsByDay(getDay())
        initUI()
    }

    override fun onResume() {
        super.onResume()
        plannerViewModel.getDataWithSetsByDay(currentlySelectedDay)
    }

    private fun initUI() {
        initList()
        initUIState()
        initListeners()
    }

    private fun initListeners() {
        binding.fabAddPlan.setOnClickListener { navigateToInsertActivity() }
        binding.fabResetSets.setOnClickListener { resetSets() }
    }

    private fun initList() {
        plannerDayAdapter =
            PlannerDayAdapter(initDay = currentlySelectedDay.ordinal) { onItemSelected(it) }
        binding.rvDay.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = plannerDayAdapter
        }

        planAdapter = PlanAdapter() { onItemPlanSelected(it) }
        binding.rvPlan.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = planAdapter
        }
    }

    private fun onItemPlanSelected(planSelected: PlanWithSet) {
        navigateToPlanDetail(planSelected.plan.id)
    }

    private fun onItemSelected(daySelected: DayInfo) {
        val dayName = daySelected::class.java.simpleName
        plannerViewModel.getDataWithSetsByDay(getDayModelByString(dayName))
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plannerViewModel.days.collect() {
                    plannerDayAdapter.updateList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plannerViewModel.exercisesDay.collect() {
                    planAdapter.updateList(it)
                    planList = it
                }
            }
        }
    }

    private fun getDay(): DayModel {
        val calendar = Calendar.getInstance()
        val dayCalendar =
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        return getDayModelByString(dayCalendar!!)
    }

    private fun getDayModelByString(day: String): DayModel {
        currentlySelectedDay = DayModel.valueOf(day)
        return currentlySelectedDay
    }

    private fun resetSets() {
        val setsCount = planList.sumOf { it.sets.count { sets -> sets.isSelected } }

        val resetAlert = when {
            planList.isEmpty() -> R.string.empty_exercises
            setsCount == 0 -> R.string.exercises_already_reset
            else -> {
                plannerViewModel.resetSetsByDay(currentlySelectedDay)
                R.string.reset_exercises
            }
        }

        Toast.makeText(
            context,
            resetAlert,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun navigateToInsertActivity() {
        findNavController().navigate(PlannerFragmentDirections.actionPlannerFragmentToInsertPlanActivity())
    }

    private fun navigateToPlanDetail(id: Long) {
        findNavController().navigate(
            PlannerFragmentDirections.actionPlannerFragmentToPlanDetailActivity(
                id
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlannerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}