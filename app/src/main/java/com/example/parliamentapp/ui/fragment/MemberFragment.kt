/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Fragment for the member details.
 * Inflates the layout and creates a view model with values to observe.
 * Adapter is used to show comments with recyclerview.
 */

package com.example.parliamentapp.ui.fragment

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentapp.R
import com.example.parliamentapp.databinding.FragmentMemberBinding
import com.example.parliamentapp.ui.adapter.CommentAdapter
import com.example.parliamentapp.ui.adapter.CommentListener
import com.example.parliamentapp.ui.viewmodel.MemberViewModel

class MemberFragment : Fragment() {

    private lateinit var binding: FragmentMemberBinding
    private lateinit var viewModel: MemberViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val application = requireNotNull(this.activity).application
        val arguments = MemberFragmentArgs.fromBundle(requireArguments())

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // creates a view model with selected member as the argument
        viewModel = ViewModelProvider(this, MemberViewModelFactory(application, arguments.personNumber))
            .get(MemberViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = CommentAdapter(CommentListener { comment ->
            viewModel.deleteComment(comment)
        })

        binding.recyclerComments.adapter = adapter

        // observe changes in comments
        viewModel.comments.observe(viewLifecycleOwner, { comments ->
            comments?.let { adapter.submitList(comments) }
        })

        // save comment on button click if something is written in the text field
        binding.buttonSave.setOnClickListener {
            if (binding.editComment.text.isNotEmpty()) {
                viewModel.saveComment(binding.editComment.text.toString())
                binding.editComment.text = null
            }
        }

        return binding.root
    }

}

class MemberViewModelFactory(val application: Application, val personNumber: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(MemberViewModel::class.java))
            return MemberViewModel(application, personNumber) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}