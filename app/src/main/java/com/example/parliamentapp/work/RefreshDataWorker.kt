package com.example.parliamentapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.parliamentapp.database.member.MemberDatabase.Companion.getDatabase
import com.example.parliamentapp.repository.MemberRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.parliamentapp.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MemberRepository(database)

        try {
            repository.refresh()
            return Result.success()
        } catch (e: HttpException) {
            return Result.retry()
        }
    }
}