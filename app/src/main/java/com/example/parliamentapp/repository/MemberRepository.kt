package com.example.parliamentapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parliamentapp.api.MemberApi
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class MemberRepository(private val database: MemberDatabase) {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    init {
        _status.postValue(Status.DONE)
    }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            try {
                Timber.d("refreshing database...")
                _status.postValue(Status.LOADING)
                val members = MemberApi.service.getMembers()
                database.dao.insert(members)
                _status.postValue(Status.DONE)
            } catch (e: Exception) {
                Timber.e(e.message)
                _status.postValue(Status.ERROR)
            }
        }
    }

    fun getMembers(party: String) : LiveData<List<Member>> = database.dao.getMembers(party)
    fun getParties() : LiveData<List<String>> = database.dao.getParties()
    fun getMember(personNumber: Int) : LiveData<Member> = database.dao.getMember(personNumber)
}