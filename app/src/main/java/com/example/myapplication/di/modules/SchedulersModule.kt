package com.example.myapplication.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Named("IO")
    @Singleton
    @Provides
    fun provideIO(): Scheduler = Schedulers.io()

    @Named("Main")
    @Singleton
    @Provides
    fun provideMainThread(): Scheduler = AndroidSchedulers.mainThread()

}