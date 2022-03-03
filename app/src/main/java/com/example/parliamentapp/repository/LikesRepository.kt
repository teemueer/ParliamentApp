package com.example.parliamentapp.repository

import androidx.lifecycle.LiveData
import com.example.parliamentapp.database.likes.Likes
import com.example.parliamentapp.database.likes.LikesDatabase
import timber.log.Timber

class LikesRepository(private val database: LikesDatabase) {

    suspend fun insert(likes: Likes) {
        database.dao.insert(likes)
    }

    fun getLikes(personNumber: Int) : LiveData<Int> = database.dao.getLikes(personNumber)

    suspend fun like(personNumber: Int) {
        database.dao.update(personNumber, 1)
    }

    suspend fun dislike(personNumber: Int) {
        database.dao.update(personNumber, -1)
    }

}