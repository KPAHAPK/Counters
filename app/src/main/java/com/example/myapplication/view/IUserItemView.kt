package com.example.myapplication.view

interface IUserItemView : IItemView {
    fun setLogin(login: String?)
    fun setId(id: String?)
    fun loadAvatar(avatar: String?)
}