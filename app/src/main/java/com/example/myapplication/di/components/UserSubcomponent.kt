package com.example.myapplication.di.components

import com.example.myapplication.di.modules.UserModule
import com.example.myapplication.di.scope.UserScope
import com.example.myapplication.presenter.UsersPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
}
