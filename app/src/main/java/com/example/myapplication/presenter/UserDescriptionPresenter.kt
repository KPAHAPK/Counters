package com.example.myapplication.presenter

import com.example.myapplication.GitHubUser
import com.example.myapplication.UserDescriptionView
import com.example.myapplication.screens.AndroidScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDescriptionPresenter(val router: Router) :
    MvpPresenter<UserDescriptionView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        return true
    }


}