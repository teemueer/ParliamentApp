package com.example.parliamentapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentapp.database.likes.Likes
import com.example.parliamentapp.database.likes.LikesDatabase
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.repository.LikesRepository
import com.example.parliamentapp.repository.MemberRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MemberViewModel(application: Application, private val personNumber: Int) : ViewModel() {
    private val memberRepository = MemberRepository(MemberDatabase.getInstance(application))
    private val likesRepository = LikesRepository(LikesDatabase.getInstance(application))

    private var _member: LiveData<Member> = memberRepository.getMember(personNumber)
    val member: LiveData<Member> get() = _member

    private var _likes: LiveData<Int> = likesRepository.getLikes(personNumber)
    val likes: LiveData<Int> get() = _likes

    fun like() {
        viewModelScope.launch {
            val likes = _likes.value ?: 0
            if (likes < 1)
                likesRepository.insert(Likes(personNumber, likes + 1))
        }
    }

    fun dislike() {
        viewModelScope.launch {
            val likes = _likes.value ?: 0
            if (likes > -1)
                likesRepository.insert(Likes(personNumber, likes - 1))
        }
    }
}
