package com.wallstreet.data.LocalDatabase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wallstreet.Application
import com.wallstreet.data.model.University
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [University::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun universitydao(): UniversityDao

    companion object {
        var instance: RoomDataBase? = null
            get() {
                if (field == null)
                    kotlin.synchronized(RoomDataBase::class) {
                        field = Room.databaseBuilder(
                                Application.appContext,
                                RoomDataBase::class.java,
                                "UniversitydataBase").build()
                    }
                return field
            }
    }

}