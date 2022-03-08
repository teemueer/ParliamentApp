/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Comments schema and data access object
 */

package com.example.parliamentapp.database.comment

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = true)    val id: Long,
    @ColumnInfo(name = "person_number") val personNumber: Int,
    @ColumnInfo(name = "comment")       val comment: String,
    @ColumnInfo(name = "date")          val date: Long = System.currentTimeMillis()
)

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)

    @Query("SELECT * FROM comments WHERE person_number = :personNumber ORDER BY date DESC")
    fun getComments(personNumber: Int): LiveData<List<Comment>>

    @Delete
    suspend fun delete(comment: Comment)
}
