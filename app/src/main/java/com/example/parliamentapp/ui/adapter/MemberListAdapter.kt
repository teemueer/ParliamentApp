/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Implements RecyclerView ListAdapter using data binding to display a list of members.
 */

package com.example.parliamentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.databinding.MemberItemViewBinding

class MemberListAdapter(val clickListener: MemberListener) : ListAdapter<Member, MemberListAdapter.ViewHolder>(MemberListDiffCallback()) {

    // called when a new viewholder is needed to represent an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    // display data at specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member: Member = getItem(position)
        holder.bind(member, clickListener)
    }

    class ViewHolder(val binding: MemberItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        // bind xml items
        fun bind(item: Member, clickListener: MemberListener) {
            binding.member = item
            binding.memberListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemberItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class MemberListDiffCallback : DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Member, newItem: Member)= oldItem.personNumber == newItem.personNumber
}

class MemberListener(val clickListener: (member: Member) -> Unit) {
    fun onClick(member: Member) = clickListener(member)
}
