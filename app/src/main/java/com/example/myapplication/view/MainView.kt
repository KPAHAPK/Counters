package com.example.myapplication.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {}

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersListView : MvpView {
    fun init()
    fun updateList()
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDescriptionView : MvpView {
    fun init()
    fun updateList()
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserRepositoryInfoView : MvpView {
    fun init()
}

