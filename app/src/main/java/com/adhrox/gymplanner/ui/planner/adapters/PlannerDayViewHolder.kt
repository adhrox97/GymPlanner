package com.adhrox.gymplanner.ui.planner.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.databinding.ItemDayBinding
import com.adhrox.gymplanner.domain.model.DayInfo

class PlannerDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemDayBinding.bind(view)

    fun render(
        dayInfo: DayInfo,
        onItemSelected: (DayInfo) -> Unit,
        selectedItem: Int,
        setSelectedItem: (Int) -> Unit
    ) {
        val context = binding.tvDay.context
        val positionState = adapterPosition == selectedItem

        binding.tvDay.text = context.getString(dayInfo.name).substring(0, 3).uppercase()

        binding.root.isSelected = positionState
        binding.tvDay.isSelected = positionState

        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                setSelectedItem(position)
            }
            onItemSelected(dayInfo)
        }
    }
}