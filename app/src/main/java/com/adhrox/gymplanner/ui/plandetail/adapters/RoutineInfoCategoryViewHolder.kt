package com.adhrox.gymplanner.ui.plandetail.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.databinding.ItemRoutineInfoBinding
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.ui.plandetail.PlanDetailState

class RoutineInfoCategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemRoutineInfoBinding.bind(view)
    fun render(routineInfoCategory: RoutineInfoCategory, planDetail: PlanDetailState.Success, onItemSelected: (RoutineInfoCategory) -> Unit){

        val context = binding.tvRoutineInfoName.context

        binding.ivRoutineInfo.setImageResource(routineInfoCategory.icon)
        binding.tvRoutineInfoName.text = context.getString(routineInfoCategory.infoName)

        val textRoutineInfo = when(routineInfoCategory){
            RoutineInfoCategory.Duration -> planDetail.duration
            RoutineInfoCategory.Sets -> planDetail.sets
            RoutineInfoCategory.Reps -> planDetail.reps
            RoutineInfoCategory.Rest -> planDetail.rest
        }

        binding.tvRoutineInfoData.text = textRoutineInfo.toString()

        binding.root.setOnClickListener { onItemSelected(routineInfoCategory) }

    }

}