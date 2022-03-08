/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Implements RecyclerView ListAdapter using data binding to display a list of comments.
 */

package com.example.parliamentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentapp.database.comment.Comment
import com.example.parliamentapp.databinding.CommentItemViewBinding

class CommentAdapter(val clickListener: CommentListener) : ListAdapter<Comment, CommentAdapter.ViewHolder>(CommentDiffCallback()) {

    // called when a new viewholder is needed to represent an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    // display data at specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = getItem(position)
        holder.bind(comment, clickListener)
    }

    class ViewHolder(val binding: CommentItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        // bind xml items
        fun bind(item: Comment, clickListener: CommentListener) {
            binding.comment = item
            binding.commentListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Comment, newItem: Comment)= oldItem.comment == newItem.comment
}

class CommentListener(val clickListener: (comment: Comment) -> Unit) {
    fun onClick(comment: Comment) = clickListener(comment)
}
