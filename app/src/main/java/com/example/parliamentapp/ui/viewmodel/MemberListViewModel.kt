/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * ViewModel for the member list fragment.
 * Gets members from the member repository.
 */

package com.example.parliamentapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.repository.MemberRepository

class MemberListViewModel(application: Application, party: String) : ViewModel() {
    private val repository = MemberRepository(MemberDatabase.getInstance(application))

    val members = repository.getMembers(party)

    private val _member = MutableLiveData<Member?>()
    val member: LiveData<Member?> get() = _member

    fun onClick(member: Member) {
        _member.value = member
    }

    // called to prevent getting navigated back immediately when returning here
    fun reset() {
        _member.value = null
    }
}
