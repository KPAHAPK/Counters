package com.example.myapplication.presenter

import android.util.Log
import com.example.myapplication.UserDescriptionView
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.IUserRepositories
import com.example.myapplication.model.UserRepositories
import com.example.myapplication.view.IRepoItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class UserDescriptionPresenter(
    private val retrofitUserRepositories: IUserRepositories,
    val user: GitHubUser,
    val router: Router
) :
    MvpPresenter<UserDescriptionView>() {

    class UserRepositoriesPresenter : IUserRepositoriesPresenter {
        val userRepos = mutableListOf<UserRepositories>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null
        override fun bindView(view: IRepoItemView) {
            val repo = userRepos[view.pos]
            repo.name?.let {
                view.setRepoName(it)
            }

        }

        override fun getCount(): Int = userRepos.size
    }

    val userRepositoriesPresenter = UserRepositoriesPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadDataFromServer()
    }

    fun loadDataFromServer() {
        retrofitUserRepositories.getUserRepos(user.login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                userRepositoriesPresenter.userRepos.clear()
                userRepositoriesPresenter.userRepos.addAll(repos)
                viewState.updateList()
            }, {
                Log.e(TAG, "Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        return true
    }


}