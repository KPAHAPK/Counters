package com.example.myapplication.presenter

import android.util.Log
import com.example.myapplication.UsersListView
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.IGitHibUsersRepo
import com.example.myapplication.screens.AndroidScreens
import com.example.myapplication.view.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

const val TAG = "UsersPresenter"

class UsersPresenter(private val retrofitUsersRepo: IGitHibUsersRepo, val router: Router) :
    MvpPresenter<UsersListView>() {

    val disposable = CompositeDisposable()

    private val screens = AndroidScreens()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let {
                view.setUserIdentifiers(user.id, user.login)
            }

        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val viewState = viewState
        viewState.init()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.userDescription(usersListPresenter.users[itemView.pos]))
        }
    }

    fun loadDataFromServer() {
        retrofitUsersRepo.getGitHubUsers()
            .observeOn(AndroidSchedulers.mainThread())
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