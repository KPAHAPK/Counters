package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.components.AppComponent
import com.example.myapplication.di.components.DaggerAppComponent
import com.example.myapplication.di.components.RepositorySubcomponent
import com.example.myapplication.di.components.UserSubcomponent
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.scope.IRepositoryScopeContainer
import com.example.myapplication.di.scope.IUserScopeContainer

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    companion object {
        lateinit var instance: App

    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun initUserSubcomponent() =
        appComponent.userSubcomponent().also {
            userSubcomponent = it
        }

    override fun initRepositorySubcomponent() =
        userSubcomponent?.repositorySubcomponent().also {
            repositorySubcomponent = it
        }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}