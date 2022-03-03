package com.example.parliamentapp.database.member

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "members", primaryKeys = ["person_number"])
data class Member(
    @ColumnInfo(name = "person_number") val personNumber: Int,
    @ColumnInfo(name = "seat_number")   val seatNumber: Int,
    @ColumnInfo(name = "last_name")     val last: String,
    @ColumnInfo(name = "first_name")    val first: String,
    @ColumnInfo(name = "party")         val party: String,
    @ColumnInfo(name = "is_minister")   val minister: Boolean,
    @ColumnInfo(name = "picture")       val picture: String,
    @ColumnInfo(name = "twitter")       val twitter: String,
    @ColumnInfo(name = "year_born")     val bornYear: Int,
    @ColumnInfo(name = "constituency")  val constituency: String
)

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(members: List<Member>)

    @Query("SELECT * FROM members")
    fun getMembers(): LiveData<List<Member>>

    @Query("SELECT * FROM members WHERE party = :party ORDER BY last_name ASC")
    fun getMembers(party: String): LiveData<List<Member>>

    @Query("SELECT * FROM members WHERE person_number = :personNumber ORDER BY last_name ASC")
    fun getMember(personNumber: Int): LiveData<Member>

    @Query("SELECT DISTINCT party FROM members ORDER BY party ASC")
    fun getParties(): LiveData<List<String>>

}