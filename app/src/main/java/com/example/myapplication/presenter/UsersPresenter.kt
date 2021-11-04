package com.example.myapplication.presenter

import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.GitHubUsersRepo
import com.example.myapplication.UsersListView
import com.example.myapplication.screens.AndroidScreens
import com.example.myapplication.view.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GitHubUsersRepo, val router: Router) :
    MvpPresenter<UsersListView>() {

    val disposable = CompositeDisposable()

    private val screens = AndroidScreens()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setUserIdentifiers(user.login, user.id)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.userDescription(usersListPresenter.users[itemView.pos]))
        }
    }

    fun loadDataRX() {
        val observable : Disposable = getDataToObserve( 300)
            .take(10)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user -> usersListPresenter.users.add(user)
                       viewState.updateList() },{})
        disposable.add(observable)
    }

    fun loadDataRXFilter() {
        val observable : Disposable = getDataToObserve( 10)
            .filter{it.id % 10 == 0}
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user -> usersListPresenter.users.add(user)
                       viewState.updateList() },{})
        disposable.add(observable)
    }

    private fun getDataToObserve(delay: Long = 0) : Observable<GitHubUser>{
        return Observable.create {user ->
            for (gitHubUser in usersRepo.getUsers()){
                if (delay != 0L) {
                    Thread.sleep(delay)
                }
                user.onNext(gitHubUser)
            }
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun clearList() {
        usersListPresenter.users.clear()
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        return true
    }

}