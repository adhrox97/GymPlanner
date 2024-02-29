package com.adhrox.gymplanner.ui.planner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.domain.model.Plan
import com.adhrox.gymplanner.domain.model.PlanWithSet

class PlanAdapter(private var planList: List<PlanWithSet> = emptyList(), private val onItemPlanSelected: (PlanWithSet) -> Unit): RecyclerView.Adapter<PlanViewHolder>() {

    fun updateList(planList: List<PlanWithSet>) {
        this.planList = planList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false))
    }

    override fun getItemCount() = planList.size

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.render(planList[position], onItemPlanSelected)
    }
}