package com.example.myapplication.di.scope

import com.example.myapplication.di.components.RepositorySubcomponent

interface IRepositoryScopeContainer {
    fun initRepositorySubcomponent(): RepositorySubcomponent?
    fun releaseRepositoryScope()
}