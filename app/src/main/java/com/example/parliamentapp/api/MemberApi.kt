/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Api with moshi and retrofit.
 * Remote source serves JSON which is converted into Member objects.
 */

package com.example.parliamentapp.api

import com.example.parliamentapp.database.member.Member
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object MemberApi {
    private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val service: MemberApiService = retrofit.create(MemberApiService::class.java)
}

interface MemberApiService {
    @GET("mps.json")
    suspend fun getMembers(): List<Member>
}