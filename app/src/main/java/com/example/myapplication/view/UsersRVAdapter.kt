package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.UserBinding
import com.example.myapplication.presenter.IUserListPresenter

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: UserBinding) :
        RecyclerView.ViewHolder(binding.root),
        IUserItemView {

        override fun setLogin(login: String?) {
            binding.userLogin.text = login
        }

        override fun setId(id: String?) {
            binding.userId.text = id.toString()
        }

        override fun loadAvatar(avatar: String?) {
            imageLoader.loadInto(avatar, binding.userAvatar)
        }

        override var pos = -1

    }
}