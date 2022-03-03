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
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.databinding.FragmentPartyListBinding
import com.example.parliamentapp.repository.MemberRepository
import com.example.parliamentapp.ui.adapter.PartyListAdapter
import com.example.parliamentapp.ui.adapter.PartyListener
import com.example.parliamentapp.ui.viewmodel.PartyListViewModel

class PartyListFragment : Fragment() {

    private lateinit var binding: FragmentPartyListBinding
    private lateinit var viewModel: PartyListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val application = requireNotNull(this.activity).application

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(this, PartyListViewModelFactory(application))
            .get(PartyListViewModel::class.java)

        val adapter = PartyListAdapter(PartyListener { party ->
            viewModel.onClick(party)
        })

        binding.recyclerPartyList.adapter = adapter

        viewModel.parties.observe(viewLifecycleOwner, { parties ->
            parties?.let { adapter.submitList(parties) }
        })

        viewModel.party.observe(viewLifecycleOwner, { party ->
            party?.let {
                this.findNavController().navigate(PartyListFragmentDirections.actionFragmentPartyListToFragmentMemberList(party))
                viewModel.reset()
            }
        })

        return binding.root
    }
}

class PartyListViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(PartyListViewModel::class.java))
            return PartyListViewModel(application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}