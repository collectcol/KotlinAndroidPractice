package com.my.doha.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.doha.dao.UserDataDao
import com.my.doha.data.UserData

@Database(entities = [UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDataDao(): UserDataDao
}