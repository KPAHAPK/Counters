package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainBinding: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnCounter1.setOnClickListener { presenter.counterClick(CountersEnum.COUNTER_1) }
        mainBinding.btnCounter2.setOnClickListener { presenter.counterClick(CountersEnum.COUNTER_2) }
        mainBinding.btnCounter3.setOnClickListener { presenter.counterClick(CountersEnum.COUNTER_3) }
    }

    override fun setButtonOneText(text: String) {
        mainBinding.btnCounter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        mainBinding.btnCounter2.text = text
    }

    override fun setButtonThreeText(text: String) {
        mainBinding.btnCounter3.text = text
    }
}