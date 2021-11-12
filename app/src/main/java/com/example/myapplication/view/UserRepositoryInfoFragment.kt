package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentUserRepositoryInfoBinding
import com.example.myapplication.model.UserRepository
import com.example.myapplication.presenter.UserRepositoryInfoPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserRepositoryInfoFragment() :
    MvpAppCompatFragment(), UserRepositoryInfoView, BackButtonListener {

    private var _binding: FragmentUserRepositoryInfoBinding? = null
    private val binding: FragmentUserRepositoryInfoBinding
        get() = _binding!!

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: UserRepository) = UserRepositoryInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    private lateinit var repository: UserRepository

    val presenter by moxyPresenter {
        repository = arguments?.getParcelable<UserRepository>(REPOSITORY_ARG) as UserRepository
        UserRepositoryInfoPresenter(repository).apply {
            App.instance.appComponent.inject(this)
        }
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