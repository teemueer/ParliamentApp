package com.example.parliamentapp.database.comment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class CommentDatabase: RoomDatabase() {
    abstract val dao: CommentDao

    companion object {
        @Volatile
        private var INSTANCE: CommentDatabase? = null

        fun getInstance(context: Context): CommentDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // Create a new database if one doesn't exist yet
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CommentDatabase::class.java,
                        "comments")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}