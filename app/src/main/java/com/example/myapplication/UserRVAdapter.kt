package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.UserBinding

class UserRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener{
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: UserRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: UserBinding) : RecyclerView.ViewHolder(vb.root), UserItemView {
        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override var pos = -1

    }
}