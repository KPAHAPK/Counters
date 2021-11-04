package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.UserReposityItemBinding
import com.example.myapplication.presenter.IUserRepositoriesPresenter

class UserRepositoriesRVAdapter(val presenter: IUserRepositoriesPresenter) :
    RecyclerView.Adapter<UserRepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            UserReposityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: UserReposityItemBinding) :
        RecyclerView.ViewHolder(binding.root), IRepoItemView {
        override fun setRepoName(repoName: String) {
            binding.repositoriesName.text = repoName
        }

        override var pos = -1

    }
}