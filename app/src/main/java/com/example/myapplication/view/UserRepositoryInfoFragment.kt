package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.UserRepositoryInfoView
import com.example.myapplication.databinding.FragmentUserRepositoryInfoBinding
import com.example.myapplication.model.UserRepository
import com.example.myapplication.presenter.UserRepositoryInfoPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_1 = "KEY"

class UserRepositoryInfoFragment() :
    MvpAppCompatFragment(), UserRepositoryInfoView, BackButtonListener {

    private var _binding: FragmentUserRepositoryInfoBinding? = null
    private val binding: FragmentUserRepositoryInfoBinding
        get() = _binding!!

    companion object {
        fun newInstance(repo: UserRepository): UserRepositoryInfoFragment {
            val args = Bundle()
            args.putParcelable(ARG_1, repo)
            return UserRepositoryInfoFragment().apply {
                this.arguments = args
            }
        }
    }

    val presenter by moxyPresenter {
        UserRepositoryInfoPresenter()
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
        val repo = arguments?.getParcelable<UserRepository>(ARG_1)
        binding.repositoryName.text = repo?.repoName
        binding.forkTitile.text = getString(R.string.fork, repo?.forksCount.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}