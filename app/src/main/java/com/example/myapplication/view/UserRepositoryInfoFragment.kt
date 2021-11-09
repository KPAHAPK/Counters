package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.UserRepositoryInfoView
import com.example.myapplication.databinding.FragmentUserRepositoryInfoBinding
import com.example.myapplication.model.UserRepository
import com.example.myapplication.presenter.UserRepositoryInfoPresenter
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val ARG_1 = "KEY"

class UserRepositoryInfoFragment() :
    MvpAppCompatFragment(), UserRepositoryInfoView, BackButtonListener {

    @Inject
    lateinit var router: Router

    private var _binding: FragmentUserRepositoryInfoBinding? = null
    private val binding: FragmentUserRepositoryInfoBinding
        get() = _binding!!

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: UserRepository) = UserRepositoryInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
            App.instance.appComponent.inject(this)
        }
    }

    lateinit var repository: UserRepository

    val presenter by moxyPresenter {
        repository = arguments?.getParcelable<UserRepository>(REPOSITORY_ARG) as UserRepository
        UserRepositoryInfoPresenter(router, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserRepositoryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init() {
        binding.repositoryName.text = repository?.name
        binding.forkTitile.text = getString(R.string.fork, repository?.forksCount.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}