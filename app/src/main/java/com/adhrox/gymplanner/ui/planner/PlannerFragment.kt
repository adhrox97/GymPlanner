package com.adhrox.gymplanner.ui.planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.adhrox.gymplanner.databinding.FragmentPlannerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlannerFragment : Fragment() {

    private val plannerViewModel by viewModels<PlannerViewModel>()

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

    }

    private fun initUIState() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlannerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}