package com.example.parliamentapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.parliamentapp.api.MemberApi
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.database.member.MemberDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class MemberRepository(private val database: MemberDatabase) {

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            try {
                Timber.d("refreshing database...")
                val members = MemberApi.service.getMembers()
                database.dao.insert(members)
            } catch (e: Exception) {
                Timber.e(e.message)
            }
        }
    }

    fun getMembers(party: String) : LiveData<List<Member>> = database.dao.getMembers(party)

    fun getParties() : LiveData<List<String>> = database.dao.getParties()

    fun getMember(personNumber: Int) : LiveData<Member> = database.dao.getMember(personNumber)
}