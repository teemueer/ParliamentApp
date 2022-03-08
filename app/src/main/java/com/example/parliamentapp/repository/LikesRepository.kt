/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Repository for fetching likes from local database.
 */

package com.example.parliamentapp.repository

import androidx.lifecycle.LiveData
import com.example.parliamentapp.database.likes.Likes
import com.example.parliamentapp.database.likes.LikesDatabase

class LikesRepository(private val database: LikesDatabase) {

    suspend fun insert(likes: Likes) {
        database.dao.insert(likes)
    }

    fun getLikes(personNumber: Int) : LiveData<Int>
        = database.dao.getLikes(personNumber)

    suspend fun like(personNumber: Int) {
        database.dao.update(personNumber, 1)
    }

    suspend fun dislike(personNumber: Int) {
        database.dao.update(personNumber, -1)
    }

}