package com.example.parliamentapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.repository.MemberRepository
import timber.log.Timber

class MemberListViewModel(memberRepository: MemberRepository, party: String) : ViewModel() {
    val members = memberRepository.getMembers(party)

    private val _member = MutableLiveData<Member?>()
    val member: LiveData<Member?> get() = _member

    fun onClick(member: Member) {
        _member.value = member
    }

    fun reset() {
        _member.value = null
    }
}
