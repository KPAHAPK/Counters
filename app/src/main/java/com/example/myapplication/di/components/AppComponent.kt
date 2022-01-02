package com.example.myapplication.di.components

import com.example.myapplication.di.modules.*
import com.example.myapplication.presenter.MainPresenter
import com.example.myapplication.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        SchedulersModule::class
    ]
)
interface AppComponent {

    fun userSubcomponent(): UserSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}