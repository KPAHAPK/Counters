package com.example.myapplication

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun counterClick(counter: CountersEnum) {
        when (counter) {
            CountersEnum.COUNTER_1 -> processCounterClick(counter)
            CountersEnum.COUNTER_2 -> processCounterClick(counter)
            CountersEnum.COUNTER_3 -> processCounterClick(counter)
        }
    }

    private fun processCounterClick(counter: CountersEnum) {
        val index = counter.ordinal
        val nextValue = model.next(index)
        view.setButtonText(counter, nextValue.toString())
    }
}