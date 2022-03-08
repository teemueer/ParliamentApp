/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Repository for fetching members from local database and refreshing the data
 * from a remote source using api.
 */

package com.example.parliamentapp.repository

import androidx.lifecycle.LiveData
import com.example.parliamentapp.api.MemberApi
import com.example.parliamentapp.database.member.Member
import com.example.parliamentapp.database.member.MemberDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class MemberRepository(private val database: MemberDatabase) {

    // fetch member data from remote source
    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            try {
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