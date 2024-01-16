package com.adhrox.gymplanner.ui.planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhrox.gymplanner.databinding.FragmentPlannerBinding
import com.adhrox.gymplanner.ui.planner.adapters.PlanAdapter
import com.adhrox.gymplanner.ui.planner.adapters.PlannerDayAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlannerFragment : Fragment() {

    private val plannerViewModel by viewModels<PlannerViewModel>()
    private lateinit var plannerDayAdapter: PlannerDayAdapter
    private lateinit var planAdapter: PlanAdapter

    private var _binding: FragmentPlannerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        plannerDayAdapter = PlannerDayAdapter(onItemSelected = {})
        binding.rvDay.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = plannerDayAdapter
        }

        planAdapter = PlanAdapter()
        binding.rvPlan.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = planAdapter
        }

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                plannerViewModel.days.collect(){
                    plannerDayAdapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlannerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}