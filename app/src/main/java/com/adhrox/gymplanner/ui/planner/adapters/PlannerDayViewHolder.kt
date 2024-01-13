package com.adhrox.gymplanner.ui.planner.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.databinding.ItemDayBinding
import com.adhrox.gymplanner.domain.model.DayInfo

class PlannerDayViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemDayBinding.bind(view)

    fun render(dayInfo: DayInfo){

    }
}