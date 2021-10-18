package com.example.myapplication

import moxy.MvpPresenter

class MainPresenter(private val model: CountersModel) : MvpPresenter<MainView>() {

    fun counterOneClick() {
      val nextValue = model.next(0)
        viewState.setButtonOneText(nextValue.toString())
    }
    fun counterTwoClick() {
      val nextValue = model.next(1)
        viewState.setButtonOneText(nextValue.toString())
    }
    fun counterThreeClick() {
      val nextValue = model.next(2)
        viewState.setButtonOneText(nextValue.toString())
    }
}