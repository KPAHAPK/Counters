package com.example.myapplication.view

import com.example.myapplication.IItemView

interface IUserItemView : IItemView {
    fun setLogin(text: String)
}