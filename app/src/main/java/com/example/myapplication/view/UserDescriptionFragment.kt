package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.BackButtonListener
import com.example.myapplication.GitHubUser
import com.example.myapplication.UserDescriptionView
import com.example.myapplication.databinding.FragmentUserDecriptionBinding
import com.example.myapplication.presenter.UserDescriptionPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_1 = "user"

class UserDescriptionFragment : MvpAppCompatFragment(), UserDescriptionView, BackButtonListener {

    private var _binding: FragmentUserDecriptionBinding? = null
    private val binding : FragmentUserDecriptionBinding
        get() = _binding!!

    companion object {
        fun newInstance(user: GitHubUser): UserDescriptionFragment {
            val args = Bundle()
            args.putParcelable(ARG_1, user)
            return UserDescriptionFragment().apply {
                this.arguments = args
            }
        }
    }


    val presenter by moxyPresenter { UserDescriptionPresenter(App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDecriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        val user = arguments?.getParcelable<GitHubUser>(ARG_1)
        val userLogin = user?.login
        val userId = user?.id
        binding.textView.text = userLogin + userId
    }


    override fun onBackPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}


