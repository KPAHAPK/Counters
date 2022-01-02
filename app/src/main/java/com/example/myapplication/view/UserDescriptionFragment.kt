package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentUserDecriptionBinding
import com.example.myapplication.di.components.RepositorySubcomponent
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.presenter.UserDescriptionPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDescriptionFragment() :
    MvpAppCompatFragment(),
    UserDescriptionView, BackButtonListener {

    private var _binding: FragmentUserDecriptionBinding? = null
    private val binding: FragmentUserDecriptionBinding
        get() = _binding!!

    private var adapter: UserRepositoriesRVAdapter? = null

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GitHubUser) = UserDescriptionFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter by moxyPresenter {
        user = arguments?.getParcelable<GitHubUser>(USER_ARG) as GitHubUser
        UserDescriptionPresenter(user).apply {
            repositorySubcomponent = App.instance.initRepositorySubcomponent()
            repositorySubcomponent?.inject(this)
        }
    }

    private lateinit var user: GitHubUser

    var imageLoader = GlideImageLoader()

    var repositorySubcomponent: RepositorySubcomponent? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDecriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {

        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvRepositories.layoutManager = linearLayoutManager
        adapter = UserRepositoriesRVAdapter(presenter.userRepositoriesPresenter)
        binding.rvRepositories.adapter = adapter

        binding.userLogin.text = user.login
        imageLoader.loadInto(user.avatarUrl, binding.userAvatar)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}


