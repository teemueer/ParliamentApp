/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * ViewModel for the party list fragment.
 * Gets parties from the member repository.
 */

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

    // called to prevent getting navigated back immediately when returning here
    fun reset() {
        _party.value = null
    }
}
