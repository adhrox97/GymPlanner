package com.adhrox.gymplanner.ui.plandetail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.ui.plandetail.PlanDetailState

class RoutineInfoCategoryAdapter(
    private var infoRoutineList: List<RoutineInfoCategory>,
    private val planDetail: PlanDetailState.Success,
    private val onItemSelected: (RoutineInfoCategory) -> Unit
) : RecyclerView.Adapter<RoutineInfoCategoryViewHolder>() {

/*    fun updateList(infoRoutineList: List<RoutineInfoCategory>){
        this.infoRoutineList = infoRoutineList
        notifyDataSetChanged()
    }*/
    private var editingStatus = false
    fun enableEditing(enable: Boolean) {
        editingStatus = enable
        updateList()
    }

    private fun updateList(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineInfoCategoryViewHolder {
        return RoutineInfoCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_routine_info, parent, false))
    }

    override fun getItemCount() = infoRoutineList.size

    override fun onBindViewHolder(holder: RoutineInfoCategoryViewHolder, position: Int) {
        holder.render(infoRoutineList[position], planDetail, editingStatus, onItemSelected)
    }
}