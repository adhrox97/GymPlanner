package com.adhrox.gymplanner.ui.plandetail.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.ItemSetBinding
import com.adhrox.gymplanner.domain.model.Set

class SetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSetBinding.bind(view)

    fun render(set: Set, onItemSelected: (Set) -> Unit) {

        val context = binding.root.context

        if (set.isSelected){
            binding.root.apply {
                setBackgroundColor(context.getColor(R.color.green))
                setImageResource(R.drawable.ic_check)
            }
        } else {
            binding.root.apply {
                setBackgroundColor(context.getColor(R.color.red))
                setImageResource(R.drawable.ic_close)
            }
        }

        binding.root.setOnClickListener { onItemSelected(set) }
    }

}