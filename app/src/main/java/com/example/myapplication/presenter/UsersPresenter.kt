package com.example.myapplication.presenter

import android.util.Log
import com.example.myapplication.UsersListView
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.IGitHubUsersRepo
import com.example.myapplication.screens.IScreens
import com.example.myapplication.view.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

const val TAG = "UsersPresenter"

class UsersPresenter(val uiSchedulers: Scheduler) :
    MvpPresenter<UsersListView>() {

    @Inject
    lateinit var screens: IScreens
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var usersRepo: IGitHubUsersRepo

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let {
                view.setLogin(user.login)
                view.setId(user.id)
                view.loadAvatar(user.avatarUrl)
            }

        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadDataFromServer()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.userDescription(usersListPresenter.users[itemView.pos]))
        }
    }

    fun loadDataFromServer() {
        usersRepo.getGitHubUsers()
            .observeOn(uiSchedulers)
            .subscribe({ usersRepo ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(usersRepo)
                viewState.updateList()
            }, {
                Log.e(TAG, "Error: ${it.message}")
            })
    }

    fun clearList() {
        usersListPresenter.users.clear()
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        return true
    }

}