package com.example.myapplication.presenter

import com.example.myapplication.UserRepositoryInfoView
import com.example.myapplication.model.UserRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserRepositoryInfoPresenter(val router: Router, val repository: UserRepository) :
    MvpPresenter<UserRepositoryInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        return true
    }
}