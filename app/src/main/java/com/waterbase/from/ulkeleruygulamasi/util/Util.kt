package com.waterbase.from.ulkeleruygulamasi.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waterbase.from.ulkeleruygulamasi.R

fun ImageView.setImageWithURL(url: String?, progressDrawable: CircularProgressDrawable) {
    val optionsFactory = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_launcher_foreground)

    Glide
        .with(context)
        .setDefaultRequestOptions(optionsFactory)
        .load(url)
        .into(this)
}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 40f
        start()
    }
}