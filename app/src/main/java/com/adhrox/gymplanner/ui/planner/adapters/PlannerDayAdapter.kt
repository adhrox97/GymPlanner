package com.adhrox.gymplanner.ui.planner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.domain.model.DayInfo

class PlannerDayAdapter(
    private var dayList: List<DayInfo> = emptyList(),
    initDay: Int,
    private val onItemSelected: (DayInfo) -> Unit
) :
    RecyclerView.Adapter<PlannerDayViewHolder>() {

    private var selectedItem = initDay

    fun updateList(dayList: List<DayInfo>) {
        this.dayList = dayList
        notifyDataSetChanged()
    }

    private fun setSelectedItem(selectedItem: Int) {
        this.selectedItem = selectedItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannerDayViewHolder {
        return PlannerDayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        )
    }

    override fun getItemCount() = dayList.size

    override fun onBindViewHolder(holder: PlannerDayViewHolder, position: Int) {

        holder.render(dayList[position], onItemSelected, selectedItem) { setSelectedItem(it) }

    }
}