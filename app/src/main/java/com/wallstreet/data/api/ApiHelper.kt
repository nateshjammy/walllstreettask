package com.wallstreet.data.api

import com.wallstreet.data.model.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUniversity(country: String = "") = withContext(Dispatchers.IO) {

        try {
            val response = apiService.getUniversity(country)
            if (response.isSuccessful && response.code() == 200) {
                Triple(response.body(), true, response.message())

            } else Triple(null, false, response.message())

        } catch (e: Exception) {
            Triple(null, false, e.message.toString())
        }
    }


}