package com.example.parliamentapp.repository

import androidx.lifecycle.LiveData
import com.example.parliamentapp.database.comment.Comment
import com.example.parliamentapp.database.comment.CommentDatabase

class CommentRepository(private val database: CommentDatabase) {

    suspend fun insert(comment: Comment) {
        database.dao.insert(comment)
    }

    fun getComments(personNumber: Int) : LiveData<List<Comment>>
        = database.dao.getComments(personNumber)

    suspend fun delete(comment: Comment) {
        database.dao.delete(comment)
    }
}