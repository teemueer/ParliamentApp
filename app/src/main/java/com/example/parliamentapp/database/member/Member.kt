package com.example.parliamentapp.database.member

import androidx.room.ColumnInfo
import androidx.room.Entity

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