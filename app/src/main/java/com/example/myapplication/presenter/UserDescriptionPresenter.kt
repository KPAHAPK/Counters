package com.example.myapplication.presenter

import com.example.myapplication.UserDescriptionView
import com.example.myapplication.model.GitHubUser
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDescriptionPresenter(router: Router, val gitHubUser: GitHubUser?) :
    MvpPresenter<UserDescriptionView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        gitHubUser?.login?.let { viewState.init() }

    }

    fun backPressed(): Boolean {
        return true
    }


}