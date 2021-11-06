package com.example.myapplication.view

import com.example.myapplication.IItemView

interface IRepoItemView : IItemView {
    fun setRepoName(name: String?)
}