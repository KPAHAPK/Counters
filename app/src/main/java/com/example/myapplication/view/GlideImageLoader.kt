package com.example.myapplication.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myapplication.R

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String?, container: ImageView) {
        Glide.with(container)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(container)
    }
}