package com.example.parliamentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentapp.R
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.databinding.FragmentMemberListBinding
import com.example.parliamentapp.repository.MemberRepository
import com.example.parliamentapp.ui.adapter.MemberListAdapter
import com.example.parliamentapp.ui.adapter.MemberListener
import com.example.parliamentapp.ui.viewmodel.MemberListViewModel

class MemberListFragment : Fragment() {

    private lateinit var binding: FragmentMemberListBinding
    private lateinit var viewModel: MemberListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(this.activity).application
        val database = MemberDatabase.getDatabase(application)
        val arguments = MemberListFragmentArgs.fromBundle(requireArguments())

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, MemberListViewModelFactory(database, arguments.party))
            .get(MemberListViewModel::class.java)

        val adapter = MemberListAdapter(MemberListener { member ->
            viewModel.onClick(member)
        })

        binding.recyclerMemberList.adapter = adapter

        viewModel.members.observe(viewLifecycleOwner, { members ->
            members?.let { adapter.submitList(members) }
        })

        return binding.root
    }
}

class MemberListViewModelFactory(private val database: MemberDatabase, private val party: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(MemberListViewModel::class.java))
            return MemberListViewModel(MemberRepository(database), party) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}