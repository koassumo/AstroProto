package com.example.astroproto.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.astroproto.model.IImageLoader


class GladeImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container )
    }

}