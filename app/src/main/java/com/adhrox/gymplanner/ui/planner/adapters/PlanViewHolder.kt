package com.adhrox.gymplanner.ui.planner.adapters

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.ItemPlanBinding
import com.adhrox.gymplanner.domain.model.PlanWithSet

class PlanViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPlanBinding.bind(view)

    fun render(planWithSet: PlanWithSet, onItemPlanSelected: (PlanWithSet) -> Unit) {
        val context = binding.tvDay.context

        binding.tvExercise.text = planWithSet.plan.exercise
        binding.tvDay.text = context.getString(planWithSet.plan.day.refDay)
        binding.tvRepsCount.text = getStringRepsCount(planWithSet)
        binding.root.setOnClickListener { onItemPlanSelected(planWithSet) }
    }

    private fun getStringRepsCount(planWithSet: PlanWithSet): String {
        val context = binding.tvRepsCount.context
        val ofString = context.getString(R.string.of)
        val setsDone = planWithSet.sets.count { it.isSelected }
        val setsSize = planWithSet.sets.size

        setRepsCountColor(setsSize, setsDone, context)

        return "$setsDone $ofString $setsSize "
    }

    private fun setRepsCountColor(setsSize: Int, setsDone: Int, context: Context) {
        val cardViewBackgroundColor = when {
            setsSize == 0 -> R.color.white
            setsDone == setsSize -> R.color.green
            else -> R.color.yellow
        }
        binding.cvSetsState.setCardBackgroundColor(
            ContextCompat.getColor(
                context,
                cardViewBackgroundColor
            )
        )
    }
}