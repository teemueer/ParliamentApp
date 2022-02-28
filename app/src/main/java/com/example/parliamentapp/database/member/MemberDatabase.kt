package com.example.parliamentapp.database.member

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Member>)

    @Query("SELECT * FROM members")
    fun getMembers(): LiveData<List<Member>>

    @Query("SELECT * FROM members WHERE party = :party ORDER BY last_name ASC")
    fun getMembers(party: String): LiveData<List<Member>>

    @Query("SELECT * FROM members WHERE person_number = :personNumber ORDER BY last_name ASC")
    fun getMember(personNumber: Int): LiveData<Member>

    @Query("SELECT DISTINCT party FROM members ORDER BY party ASC")
    fun getParties(): LiveData<List<String>>

}

@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class MemberDatabase: RoomDatabase() {
    abstract val dao: MemberDao

    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getDatabase(context: Context): MemberDatabase {
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