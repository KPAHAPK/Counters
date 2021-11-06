package com.example.myapplication.view

import com.example.myapplication.IItemView

interface IUserItemView : IItemView {
    fun setLogin(login: String?)
    fun setId(id: String?)
    fun loadAvatar(avatar: String?)
}