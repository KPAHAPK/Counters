package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.UserDescriptionView
import com.example.myapplication.databinding.FragmentUserDecriptionBinding
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.presenter.UserDescriptionPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_1 = "user"

class UserDescriptionFragment(val imageLoader: GlideImageLoader) : MvpAppCompatFragment(), UserDescriptionView, BackButtonListener {

    private var _binding: FragmentUserDecriptionBinding? = null
    private val binding: FragmentUserDecriptionBinding
        get() = _binding!!

    companion object {
        fun newInstance(user: GitHubUser): UserDescriptionFragment {
            val args = Bundle()
            args.putParcelable(ARG_1, user)
            return UserDescriptionFragment(GlideImageLoader()).apply {
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        val user = arguments?.getParcelable<GitHubUser>(ARG_1)
        binding.userLogin.text = user?.login
        imageLoader.loadInto(user?.avatarUrl, binding.userAvatar)
    }


    override fun onBackPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}


