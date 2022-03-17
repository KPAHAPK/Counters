package com.example.myapplication

import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.IGitHubUsersRepo
import com.example.myapplication.presenter.IUserListPresenter
import com.example.myapplication.presenter.UsersPresenter
import com.example.myapplication.view.`UsersListView$$State`
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UsersPresenterTest {
    @Mock
    private lateinit var usersRepo: IGitHubUsersRepo

    @Mock
    private lateinit var usersListView: `UsersListView$$State`


    private lateinit var usersPresenter: UsersPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        usersPresenter = UsersPresenter()
        usersPresenter.usersRepo = usersRepo
        usersPresenter.uiSchedulers = Schedulers.trampoline()
        usersPresenter.setViewState(usersListView)
    }

    private fun getFakeUsersList() = listOf(
        GitHubUser("1", "aaa", null),
        GitHubUser("2", "bbb", null),
        GitHubUser("3", "ccc", null))

    @Test
    fun loadDataFromServer_test(){
        val usersList = getFakeUsersList()
        Mockito.`when`(usersPresenter.usersRepo.getGitHubUsers()).thenReturn(Single.just(usersList))

        usersPresenter.loadDataFromServer()

        verify(usersPresenter.usersRepo).getGitHubUsers()
        verify(usersPresenter.viewState).updateList()
    }

}