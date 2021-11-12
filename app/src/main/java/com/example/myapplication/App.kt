package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.components.AppComponent
import com.example.myapplication.di.components.DaggerAppComponent
import com.example.myapplication.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App

    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}