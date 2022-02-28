package com.example.parliamentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentapp.databinding.PartyItemViewBinding
import com.example.parliamentapp.util.bindParties

class PartyListAdapter(val clickListener: PartyListener) : ListAdapter<String, PartyListAdapter.ViewHolder>(PartyListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val party: String = getItem(position)
        holder.bind(party, clickListener)
        holder.binding.buttonParty.bindParties(party)
    }

    class ViewHolder(val binding: PartyItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, clickListener: PartyListener) {
            binding.party = item
            binding.partyListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PartyItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PartyListDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
}

class PartyListener(val clickListener: (party: String) -> Unit) {
    fun onClick(party: String) = clickListener(party)
}
