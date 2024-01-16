package com.adhrox.gymplanner.ui.planner.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.databinding.ItemPlanBinding
import com.adhrox.gymplanner.domain.model.Plan

class PlanViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemPlanBinding.bind(view)

    fun render(plan: Plan){
        binding.tvId.text = plan.id.toString()
        binding.tvEjercicio.text = plan.ejercicio
        binding.tvDia.text = plan.dia.name
    }
}