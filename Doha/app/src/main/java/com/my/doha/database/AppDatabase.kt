package com.my.doha.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.my.doha.dao.UserDataDao
import com.my.doha.data.UserData

@Database(entities = [UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDataDao(): UserDataDao
}
object DatabaseProvider {
    private var db: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            ).build()
        }
        return db!!
    }
}