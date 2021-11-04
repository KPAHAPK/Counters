package com.example.myapplication.view

import android.widget.ImageView


interface IImageLoader<in T> {
    fun loadInto(url: String?, container: T)
}