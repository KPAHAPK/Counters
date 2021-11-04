package com.example.myapplication.view

import com.example.myapplication.IItemView

interface IUserItemView : IItemView {
    fun setLogin(login: String?)
    fun setId(id: Int?)
    fun loadAvatar(avatar: String?)
}