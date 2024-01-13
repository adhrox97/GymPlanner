package com.adhrox.gymplanner.ui.planner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.domain.model.DayInfo

class PlannerDayAdapter(private var dayList: List<DayInfo> = emptyList()) :
    RecyclerView.Adapter<PlannerDayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannerDayViewHolder {
        return PlannerDayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        )
    }

    override fun getItemCount() = dayList.size

    override fun onBindViewHolder(holder: PlannerDayViewHolder, position: Int) {
        holder.render(dayList[position])
    }
}