/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Worker that updates the database with latest remote data
 */

package com.example.parliamentapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.parliamentapp.database.member.MemberDatabase
import com.example.parliamentapp.repository.MemberRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.parliamentapp.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = MemberDatabase.getInstance(applicationContext)
        val repository = MemberRepository(database)

        try {
            repository.refresh()
            return Result.success()
        } catch (e: HttpException) {
            return Result.retry()
        }
    }
}