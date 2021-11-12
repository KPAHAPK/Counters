package com.example.myapplication.presenter

import android.util.Log
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.IUserRepository
import com.example.myapplication.model.UserRepository
import com.example.myapplication.screens.IScreens
import com.example.myapplication.view.IRepoItemView
import com.example.myapplication.view.UserDescriptionView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class UserDescriptionPresenter(
    private val user: GitHubUser
) : MvpPresenter<UserDescriptionView>() {

    companion object {
        const val TAG = "UserDescriptionPresenter"
    }

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoriesRepo: IUserRepository

    @field:[Inject Named("Main")]
    lateinit var uiSchedulers: Scheduler

    class UserRepositoriesPresenter : IUserRepositoriesPresenter {
        val userRepos = mutableListOf<UserRepository>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun bindView(view: IRepoItemView) {
            val repo = userRepos[view.pos]
            repo.name?.let {
                view.setRepoName(repo.name)
            }

        }

        override fun getCount() = userRepos.size

    }

    val userRepositoriesPresenter = UserRepositoriesPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadDataFromServer()

        userRepositoriesPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.repositoryInfo(userRepositoriesPresenter.userRepos[itemView.pos]))
        }
    }

    private fun loadDataFromServer() {
        repositoriesRepo.getUserRepos(user)
            .observeOn(uiSchedulers)
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