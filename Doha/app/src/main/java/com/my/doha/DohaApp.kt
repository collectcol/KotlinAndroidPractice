package com.my.doha

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.my.doha.database.AppDatabase

class DohaApp : Application(){
    companion object {
        private var instance: DohaApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this
    }
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