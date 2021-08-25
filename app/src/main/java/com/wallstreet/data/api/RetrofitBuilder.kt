package com.wallstreet.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://universities.hipolabs.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}