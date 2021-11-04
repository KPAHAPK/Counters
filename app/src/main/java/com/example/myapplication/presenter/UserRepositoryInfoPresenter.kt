package com.example.myapplication.presenter

import com.example.myapplication.UserRepositoryInfoView
import com.example.myapplication.model.UserRepository
import moxy.MvpPresenter

class UserRepositoryInfoPresenter() : MvpPresenter<UserRepositoryInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        return true
    }
}