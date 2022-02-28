package com.example.parliamentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.databinding.MemberItemViewBinding
import com.example.parliamentapp.databinding.PartyItemViewBinding
import com.example.parliamentapp.util.bindParties

class MemberListAdapter(val clickListener: MemberListener) : ListAdapter<Member, MemberListAdapter.ViewHolder>(MemberListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member: Member = getItem(position)
        holder.bind(member, clickListener)
    }

    class ViewHolder(val binding: MemberItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

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