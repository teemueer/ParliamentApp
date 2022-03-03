package com.example.parliamentapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.repository.MemberRepository
import timber.log.Timber

class PartyListViewModel(application: Application) : ViewModel() {
    private val repository = MemberRepository(MemberDatabase.getInstance(application))

    val parties = repository.getParties()

    private val _party = MutableLiveData<String?>()
    val party: LiveData<String?> get() = _party

    fun onClick(party: String) {
        _party.value = party
    }

    fun reset() {
        _party.value = null
    }
}
