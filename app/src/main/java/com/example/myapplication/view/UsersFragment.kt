package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.App
import com.example.myapplication.AppNetworkStatus
import com.example.myapplication.UsersListView
import com.example.myapplication.api.RetrofitHolder
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.FragmentUsersBinding
import com.example.myapplication.model.RetrofitGitHubUserRepo
import com.example.myapplication.presenter.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersListView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(RetrofitGitHubUserRepo(RetrofitHolder.iDataSource, AppNetworkStatus(context),
            AppDatabase.getInstance()), App.instance.router)
    }

    var adapter: UsersRVAdapter? = null

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadDataBtn.setOnClickListener {
            presenter.loadDataFromServer()
        }

        binding.clearBtn.setOnClickListener {
            presenter.clearList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() = presenter.backPressed()

}