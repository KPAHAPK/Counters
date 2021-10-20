package com.example.myapplication.presenter

import com.example.myapplication.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView (view: V)
    fun getCount(): Int
}