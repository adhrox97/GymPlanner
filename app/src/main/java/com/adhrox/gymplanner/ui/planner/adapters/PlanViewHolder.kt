package com.adhrox.gymplanner.ui.planner.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.ItemPlanBinding
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet

class PlanViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemPlanBinding.bind(view)

    fun render(planWithSet: PlanWithSet, onItemPlanSelected: (PlanWithSet) -> Unit){

        val context = binding.tvDia.context

        binding.tvEjercicio.text = planWithSet.plan.ejercicio
        binding.tvDia.text = context.getString(planWithSet.plan.dia.refDay)
        binding.tvRepsCount.text = getStringRepsCount(planWithSet)
        binding.root.setOnClickListener { onItemPlanSelected(planWithSet) }
    }

    private fun getStringRepsCount(planWithSet: PlanWithSet): String {
        val context = binding.tvRepsCount.context
        val ofString = context.getString(R.string.of)
        val setsDone = planWithSet.sets.count{ it.isSelected }

        return "$setsDone $ofString ${planWithSet.sets.size}"
    }
}