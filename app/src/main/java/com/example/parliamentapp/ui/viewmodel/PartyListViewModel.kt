package com.example.parliamentapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.parliamentapp.repository.MemberRepository
import timber.log.Timber

class PartyListViewModel(memberRepository: MemberRepository) : ViewModel() {
    val parties = memberRepository.getParties()

    private val _party = MutableLiveData<String?>()
    val party: LiveData<String?> get() = _party

    fun onClick(party: String) {
        _party.value = party
    }

    fun reset() {
        _party.value = null
    }
}
