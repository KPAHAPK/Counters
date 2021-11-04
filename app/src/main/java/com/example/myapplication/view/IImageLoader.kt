package com.example.myapplication.view

interface IImageLoader<in T> {
    fun loadInto(url: String?, container: T)
}