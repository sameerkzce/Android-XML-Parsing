package com.example.sooryenapp.framwork.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
 

/**binding adapter to download the images from internet using glide lib
 * */
object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageURL: String?) {
        Glide.with(imageView.context)
            .load(imageURL)
            .into(imageView)
    }
}