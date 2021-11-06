package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.database.AppDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    lateinit var db: AppDatabase
    private set

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router



    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "popular-libs.db")
            .build()
    }

}