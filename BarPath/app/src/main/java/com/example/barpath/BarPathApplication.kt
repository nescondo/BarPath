package com.example.barpath

import android.app.Application
import androidx.room.Room
import edu.gvsu.cis.memorymatching.data.MyDatabase

class BarPathApplication: Application() {
    lateinit var myDB: MyDatabase
    override fun onCreate() {
        super.onCreate()
        myDB = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "my-db")
            .build()
    }
}