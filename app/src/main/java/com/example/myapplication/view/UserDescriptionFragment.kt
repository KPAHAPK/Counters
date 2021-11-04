package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.App
import com.example.myapplication.UserDescriptionView
import com.example.myapplication.api.RetrofitHolder
import com.example.myapplication.databinding.FragmentUserDecriptionBinding
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.RetrofitUserRepositories
import com.example.myapplication.presenter.UserDescriptionPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDescriptionFragment(val imageLoader: GlideImageLoader, val user: GitHubUser) :
    MvpAppCompatFragment(), UserDescriptionView, BackButtonListener {

    private var _binding: FragmentUserDecriptionBinding? = null
    private val binding: FragmentUserDecriptionBinding
        get() = _binding!!

   private lateinit var adapter: UserRepositoriesRVAdapter

    companion object {
        fun newInstance(user: GitHubUser) = UserDescriptionFragment(GlideImageLoader(), user)
    }


    val presenter by moxyPresenter {
        UserDescriptionPresenter(
            RetrofitUserRepositories(
                RetrofitHolder.iDataSource
            ), user, App.instance.router
        )
    }


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
        binding.userLogin.text = user.login
        val linearLayoutManager = LinearLayoutManager(context)
        binding.repositoriesRv.layoutManager = linearLayoutManager
        adapter = UserRepositoriesRVAdapter(presenter.userRepositoriesPresenter)
        imageLoader.loadInto(user.avatarUrl, binding.userAvatar)
    }

    override fun updateList() {

    }


    override fun onBackPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}


