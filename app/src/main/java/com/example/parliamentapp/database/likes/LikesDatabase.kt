package com.example.parliamentapp.database.likes

import android.content.Context
import androidx.room.*

@Database(entities = [Likes::class], version = 1, exportSchema = false)
abstract class LikesDatabase: RoomDatabase() {
    abstract val dao: LikesDao

    companion object {
        @Volatile
        private var INSTANCE: LikesDatabase? = null

        fun getInstance(context: Context): LikesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // Create a new database if one doesn't exist yet
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LikesDatabase::class.java,
                        "likes")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}