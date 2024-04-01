package com.adhrox.gymplanner.ui.plandetail.adapters

import android.text.InputType
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.databinding.ItemRoutineInfoBinding
import com.adhrox.gymplanner.domain.model.RoutineInfoCategory
import com.adhrox.gymplanner.ui.plandetail.PlanDetailState

class RoutineInfoCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRoutineInfoBinding.bind(view)

    fun render(
        routineInfoCategory: RoutineInfoCategory,
        planDetail: PlanDetailState.Success,
        enable: Boolean,
        onItemSelected: (RoutineInfoCategory) -> Unit
    ) {
        val context = binding.tvRoutineInfoName.context

        binding.ivRoutineInfo.setImageResource(routineInfoCategory.icon)
        binding.tvRoutineInfoName.text = context.getString(routineInfoCategory.infoName)

        val textRoutineInfo = when (routineInfoCategory) {
            RoutineInfoCategory.Duration -> planDetail.duration
            RoutineInfoCategory.Sets -> planDetail.sets
            RoutineInfoCategory.Reps -> planDetail.reps
            RoutineInfoCategory.Rest -> planDetail.rest
            RoutineInfoCategory.Weight -> planDetail.weight
        }

        binding.etRoutineInfoData.setText(textRoutineInfo.toString())

        binding.root.setOnClickListener { onItemSelected(routineInfoCategory) }

        binding.etRoutineInfoData.setOnClickListener {
            if (!enable) onItemSelected(routineInfoCategory)
        }

        if (enable) {
            binding.etRoutineInfoData.apply {
                isFocusableInTouchMode = true
                inputType = InputType.TYPE_CLASS_TEXT
                setBackgroundResource(androidx.constraintlayout.widget.R.drawable.abc_edit_text_material)
            }
        } else {
            binding.etRoutineInfoData.apply {
                isFocusableInTouchMode = false
                inputType = InputType.TYPE_NULL
                setBackgroundResource(0)
            }
        }

        binding.root.isEnabled = !enable
    }
}