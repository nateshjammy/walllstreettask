package com.wallstreet.data.repository

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.wallstreet.data.LocalDatabase.RoomDataBase
import com.wallstreet.data.api.ApiHelper
import com.wallstreet.data.model.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val apiHelper: ApiHelper) {
    val Universityresposne = MediatorLiveData<Pair<Boolean,String>>()

    suspend fun getUniversity(country: String = "") = withContext(Dispatchers.IO){
       val response =  apiHelper.getUniversity()
        if(!response.first.isNullOrEmpty()){
            RoomDataBase.instance?.universitydao()?.insertUniversityRepo(response.first!!)
        }
        Universityresposne.postValue(Pair(response.second,response.third))
    }



}