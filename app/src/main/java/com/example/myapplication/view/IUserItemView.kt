package com.example.myapplication.view

import com.example.myapplication.IItemView

interface IUserItemView : IItemView {
    fun setUserIdentifiers( id: Int?, login: String)
}