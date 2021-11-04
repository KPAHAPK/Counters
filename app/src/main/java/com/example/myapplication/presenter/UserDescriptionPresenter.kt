package com.example.myapplication.presenter

import com.example.myapplication.UserDescriptionView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDescriptionPresenter(router: Router) :
    MvpPresenter<UserDescriptionView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        return true
    }


}