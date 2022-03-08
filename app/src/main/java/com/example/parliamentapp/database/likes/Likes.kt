/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Likes schema and data access object
 */

package com.example.parliamentapp.database.likes

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "likes", primaryKeys = ["person_number"])
data class Likes(
    @ColumnInfo(name = "person_number") val personNumber: Int,
    @ColumnInfo(name = "likes")         val likes: Int,
)

@Dao
interface LikesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(likes: Likes)

    @Query("SELECT likes FROM likes WHERE person_number = :personNumber")
    fun getLikes(personNumber: Int): LiveData<Int>

    @Query("UPDATE likes SET likes = :likes WHERE person_number = :personNumber")
    suspend fun update(personNumber: Int, likes: Int)
}
