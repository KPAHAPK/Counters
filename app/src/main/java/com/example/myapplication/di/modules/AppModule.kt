package com.example.myapplication.di.modules

import com.example.myapplication.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app

}