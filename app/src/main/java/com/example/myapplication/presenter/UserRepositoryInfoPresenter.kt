package com.example.myapplication.presenter

import com.example.myapplication.model.UserRepository
import com.example.myapplication.view.UserRepositoryInfoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryInfoPresenter(val repository: UserRepository) :
    MvpPresenter<UserRepositoryInfoView>() {

    @Inject
    lateinit var router: Router

    @field:[Inject Named("Main")]
    lateinit var uiSchedulers: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        return true
    }
}