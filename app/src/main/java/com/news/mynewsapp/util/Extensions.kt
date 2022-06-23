package com.news.mynewsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun ImageView.downloadFromUrl(url: String) {
    Glide.with(context).load(url).centerCrop().into(this)
}
@BindingAdapter("android:downloadFromUrl")
fun download(imageView: ImageView, url: String?) {
    if (url != null) {
        imageView.downloadFromUrl(url)
    }
}