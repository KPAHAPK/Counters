package com.example.myapplication.di.components

import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.scope.RepositoryScope
import com.example.myapplication.presenter.UserDescriptionPresenter
import com.example.myapplication.presenter.UserRepositoryInfoPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userRepositoryInfoPresenter: UserRepositoryInfoPresenter)
    fun inject(userDescriptionPresenter: UserDescriptionPresenter)
}