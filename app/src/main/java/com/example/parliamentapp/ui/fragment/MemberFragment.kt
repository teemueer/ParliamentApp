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
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.databinding.FragmentMemberBinding
import com.example.parliamentapp.repository.MemberRepository
import com.example.parliamentapp.ui.viewmodel.MemberViewModel
import timber.log.Timber

class MemberFragment : Fragment() {

    private lateinit var binding: FragmentMemberBinding
    private lateinit var viewModel: MemberViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val application = requireNotNull(this.activity).application
        val database = MemberDatabase.getDatabase(application)
        val arguments = MemberFragmentArgs.fromBundle(requireArguments())

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(this, MemberViewModelFactory(database, arguments.personNumber))
            .get(MemberViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }
}

class MemberViewModelFactory(private val database: MemberDatabase, private val personNumber: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(MemberViewModel::class.java))
            return MemberViewModel(MemberRepository(database), personNumber) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}