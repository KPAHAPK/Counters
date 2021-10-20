package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.*
import com.example.myapplication.presenter.UsersPresenter
import com.example.myapplication.databinding.FragmentUsersBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment: MvpAppCompatFragment(), UsersListView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GitHubUsersRepo(), App.instance.router)
    }

    var adapter: UsersRVAdapter? = null

    private var _vb: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
    get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun init() {
        binding.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers?.adapter = adapter
    }

    override fun updateList() {
       adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() = presenter.backPressed()

}