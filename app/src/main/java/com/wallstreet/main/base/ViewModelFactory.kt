package com.wallstreet.main.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wallstreet.data.api.ApiHelper
import com.wallstreet.data.repository.MainRepository
import com.wallstreet.main.home.viewmodel.HomeViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}