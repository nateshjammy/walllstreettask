package com.wallstreet.data.api

import com.wallstreet.data.model.University
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("search")
    suspend fun getUniversity(@Query("country") country: String = ""):
           Response<List<University>>

}

