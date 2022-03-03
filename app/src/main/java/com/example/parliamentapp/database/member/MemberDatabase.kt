package com.example.parliamentapp.database.member

import android.content.Context
import androidx.room.*

@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class MemberDatabase: RoomDatabase() {
    abstract val dao: MemberDao

    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(context: Context): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // Create a new database if one doesn't exist yet
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemberDatabase::class.java,
                        "members")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}