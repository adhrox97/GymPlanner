package com.adhrox.gymplanner.ui.plandetail.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.databinding.ItemSetBinding
import com.adhrox.gymplanner.domain.model.Set

class SetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSetBinding.bind(view)

    fun render(set: Set, onItemSelected: (Set) -> Unit) {

        binding.cbSet.isChecked = set.isSelected

        binding.tvSetId.text = set.setId.toString()
        binding.tvPlanId.text = set.planId.toString()

        binding.root.setOnClickListener { onItemSelected(set) }
        binding.cbSet.setOnClickListener { onItemSelected(set) }
    }

}