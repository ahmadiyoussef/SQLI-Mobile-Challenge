package com.example.sqlichallengeapp.api

import com.example.sqlichallengeapp.models.UsersResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("api/users")
    suspend fun getEmployees(
        @Query("page")
        pageNumber: Int = 1
    ): UsersResponse


}
