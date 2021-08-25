package com.wallstreet.data.repository

import androidx.paging.DataSource
import com.wallstreet.data.LocalDatabase.RoomDataBase
import com.wallstreet.data.LocalDatabase.UniversityDao
import com.wallstreet.data.model.University


class UniversityDatasourceFactory(private val universityDao: UniversityDao) : DataSource.Factory<Int, University>() {


    private var SearchString = ""

    override fun create(): DataSource<Int, University> {

        return universityDao.findUniversitybyCountry(SearchString).create()

    }

    fun refreshData(searchstring : String) {

        SearchString = searchstring


    }


}