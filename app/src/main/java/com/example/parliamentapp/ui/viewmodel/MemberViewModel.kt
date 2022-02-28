package com.example.parliamentapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.repository.MemberRepository

class MemberViewModel(memberRepository: MemberRepository, personNumber: Int) : ViewModel() {
    private var _member = MutableLiveData<LiveData<Member>>()
    val member: LiveData<LiveData<Member>> get() = _member

    init {
        _member.value = memberRepository.getMember(personNumber)
    }
}
