package com.example.myapplication.presenter

import com.example.myapplication.UserRepositoryInfoView
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