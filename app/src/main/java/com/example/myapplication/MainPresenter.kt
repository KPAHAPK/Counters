package com.example.myapplication

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun counterClick(counter: CountersEnum) {
        val index = counter.ordinal
        val nextValue = model.next(index)
        when (counter) {
            CountersEnum.COUNTER_1 -> view.setButtonOneText(nextValue.toString())
            CountersEnum.COUNTER_2 -> view.setButtonTwoText(nextValue.toString())
            CountersEnum.COUNTER_3 -> view.setButtonThreeText(nextValue.toString())
        }
    }
}