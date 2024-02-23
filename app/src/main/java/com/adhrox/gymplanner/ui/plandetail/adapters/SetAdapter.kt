package com.adhrox.gymplanner.ui.plandetail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.domain.model.Set

class SetAdapter(
    private var setsList: List<Set>,
    private val onItemSelected: (Set) -> Unit
) : RecyclerView.Adapter<SetViewHolder>() {

    fun updateList(){
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        return SetViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_set, parent, false))
    }

    override fun getItemCount() = setsList.size

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.render(setsList[position], onItemSelected)
    }
}