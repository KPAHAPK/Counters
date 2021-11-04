package com.example.myapplication.presenter

import com.example.myapplication.IItemView
import com.example.myapplication.UserDescriptionView
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.view.IRepoItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDescriptionPresenter(router: Router) :
    MvpPresenter<UserDescriptionView>() {

    class UserRepositoriesPresenter: IUserRepositoriesPresenter{
        val userRepos = mutableListOf<String>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null
        override fun bindView(view: IRepoItemView) {
            val repo = userRepos[view.pos]

        }

        override fun getCount(): Int = userRepos.size

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    fun backPressed(): Boolean {
        return true
    }


}