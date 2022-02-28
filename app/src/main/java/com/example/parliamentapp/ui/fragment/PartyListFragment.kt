package com.example.parliamentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentapp.R
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.databinding.FragmentPartyListBinding
import com.example.parliamentapp.ui.adapter.PartyListAdapter
import com.example.parliamentapp.ui.adapter.PartyListener
import com.example.parliamentapp.ui.viewmodel.PartyListViewModel

class PartyListFragment : Fragment() {

    private lateinit var binding: FragmentPartyListBinding
    private lateinit var viewModel: PartyListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        viewModel = ViewModelProvider(this).get(PartyListViewModel::class.java)

        binding.lifecycleOwner = this

        val adapter = PartyListAdapter(PartyListener { party ->
            viewModel.onClick(party)
        })

        binding.recyclerPartyList.adapter = adapter

        viewModel.parties.observe(viewLifecycleOwner, { parties ->
            parties?.let { adapter.submitList(parties) }
        })

        return binding.root
    }
}