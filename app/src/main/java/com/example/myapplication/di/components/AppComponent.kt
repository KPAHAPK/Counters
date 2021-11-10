package com.example.myapplication.di.components

import com.example.myapplication.di.modules.*
import com.example.myapplication.presenter.MainPresenter
import com.example.myapplication.presenter.UserDescriptionPresenter
import com.example.myapplication.presenter.UserRepositoryInfoPresenter
import com.example.myapplication.presenter.UsersPresenter
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.UserRepositoryInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class,
        RepositoriesModule::class
    ]
)
interface AppComponent {

    //TODO Delete Fragments
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userDescriptionPresenter: UserDescriptionPresenter)
    fun inject(userRepositoryInfoFragment: UserRepositoryInfoFragment)//
    fun inject(userRepositoryInfoPresenter: UserRepositoryInfoPresenter)

}