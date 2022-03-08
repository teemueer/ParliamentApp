/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Fragment for the member list.
 * Inflates the layout and creates a view model with values to observe.
 * Adapter is used to show members with recyclerview.
 */

package com.example.parliamentapp.ui.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parliamentapp.R
import com.example.parliamentapp.databinding.FragmentMemberListBinding
import com.example.parliamentapp.ui.adapter.MemberListAdapter
import com.example.parliamentapp.ui.adapter.MemberListener
import com.example.parliamentapp.ui.viewmodel.MemberListViewModel

class MemberListFragment : Fragment() {

    private lateinit var binding: FragmentMemberListBinding
    private lateinit var viewModel: MemberListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val application = requireNotNull(this.activity).application
        val arguments = MemberListFragmentArgs.fromBundle(requireArguments())

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // creates a view model with selected party as the argument
        viewModel = ViewModelProvider(this, MemberListViewModelFactory(application, arguments.party))
            .get(MemberListViewModel::class.java)

        val adapter = MemberListAdapter(MemberListener { member ->
            viewModel.onClick(member)
        })

        binding.recyclerMemberList.adapter = adapter

        // observe changes in members
        viewModel.members.observe(viewLifecycleOwner, { members ->
            members?.let { adapter.submitList(members) }
        })

        // observe if a member is selected and navigate to member details if so
        viewModel.member.observe(viewLifecycleOwner, { member ->
            member?.let {
                this.findNavController().navigate(MemberListFragmentDirections.actionFragmentMemberListToFragmentMember(member.personNumber))
                viewModel.reset()
            }
        })

        return binding.root
    }
}

class MemberListViewModelFactory(val application: Application, val party: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(MemberListViewModel::class.java))
            return MemberListViewModel(application, party) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}