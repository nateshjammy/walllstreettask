package com.wallstreet.data.LocalDatabase

import androidx.paging.DataSource
import androidx.room.*
import com.wallstreet.data.model.University

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUniversityRepo(university: List<University>)

    @Update
    fun updateUniversityRepo(university: University): Int

    @Transaction
    @Query("SELECT * FROM University where country like '%' || :countryname || '%'")
    fun findUniversitybyCountry(countryname: String? = ""):DataSource.Factory<Int,
            University>

}