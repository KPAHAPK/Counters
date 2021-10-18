package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var mainBinding: ActivityMainBinding

    private val presenter by moxyPresenter{ MainPresenter(CountersModel())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnCounter1.setOnClickListener { presenter.counterOneClick() }
        mainBinding.btnCounter2.setOnClickListener { presenter.counterTwoClick() }
        mainBinding.btnCounter3.setOnClickListener { presenter.counterThreeClick() }
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