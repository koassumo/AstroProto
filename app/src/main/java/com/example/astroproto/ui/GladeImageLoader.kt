package com.example.astroproto.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.astroproto.model.IImageLoader


class GladeImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
//            .override(300, 100)
//            .centerCrop()
            .into(container )

        // Внимание!! .override(300, 300) рисайзит изображение для скорости rv!!!
    }
}