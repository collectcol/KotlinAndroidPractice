package com.my.doha

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.my.doha.database.AppDatabase
import com.my.doha.database.DatabaseProvider
import java.util.Calendar

class DohaApp : Application(){
    lateinit var mAuth: FirebaseAuth
    lateinit var mFirestore: FirebaseFirestore
    lateinit var mDB: AppDatabase
    lateinit var mCalendar: Calendar

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

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
        mDB = DatabaseProvider.getDatabase(this)
        mCalendar = Calendar.getInstance()
    }
}

