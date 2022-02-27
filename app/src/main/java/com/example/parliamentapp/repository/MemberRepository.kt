package com.example.parliamentapp.repository

import com.example.parliamentapp.api.MemberApi
import com.example.parliamentapp.database.member.MemberDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberRepository(private val database: MemberDatabase) {
    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val members = MemberApi.service.getMembers()
            database.dao.insert(members)
        }
    }

    fun getMembers(party: String) = database.dao.getMembers(party)
    fun getParties() = database.dao.getParties()
}