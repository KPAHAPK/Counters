package com.example.myapplication.di.scope

import com.example.myapplication.di.components.UserSubcomponent

interface IUserScopeContainer {
    fun initUserSubcomponent(): UserSubcomponent?
    fun releaseUserScope()
}