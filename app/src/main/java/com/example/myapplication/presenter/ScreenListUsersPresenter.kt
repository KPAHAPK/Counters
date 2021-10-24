package com.example.myapplication.presenter

import com.example.myapplication.GitHubUser
import com.example.myapplication.GitHubUsersRepo
import com.example.myapplication.ListUserView
import com.example.myapplication.view.IUserItemView
import moxy.MvpPresenter

class ScreenListUsersPresenter(val usersRepo: GitHubUsersRepo) : MvpPresenter<ListUserView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val userListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userListPresenter.itemClickListener = { itemView ->

        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }
}