package com.example.myapplication

import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.IUserRepository
import com.example.myapplication.model.UserRepository
import com.example.myapplication.presenter.UserDescriptionPresenter
import com.example.myapplication.view.UserDescriptionView
import com.example.myapplication.view.`UserDescriptionView$$State`
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class UserDescriptionPresenterTest {

    private lateinit var userDescriptionPresenter: UserDescriptionPresenter

    @Mock
    private lateinit var userDescriptionView: `UserDescriptionView$$State`

    @Mock
    private lateinit var repositoriesRepo: IUserRepository

    private lateinit var user: GitHubUser

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        user = getFakeGithubUser()
        userDescriptionPresenter = UserDescriptionPresenter(
            user
        )
        userDescriptionPresenter.repositoriesRepo = repositoriesRepo
        userDescriptionPresenter.uiSchedulers = Schedulers.trampoline()
        userDescriptionPresenter.setViewState(userDescriptionView)
    }

    private fun getFakeGithubUser() =
        GitHubUser("1", "aaa", null)


    @Test
    fun loadDataFromServer_test() {
        Mockito.`when`(userDescriptionPresenter.repositoriesRepo.getUserRepos(user)).thenReturn(Single.just(
            listOf(UserRepository("repId1", "repAAA", "repFork1"))))

        userDescriptionPresenter.loadDataFromServer()

        verify(userDescriptionPresenter.repositoriesRepo, Mockito.times(1)).getUserRepos(user)
        verify(userDescriptionView, Mockito.atLeast(1)).updateList()
    }
}