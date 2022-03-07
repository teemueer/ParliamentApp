package com.example.parliamentapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.parliamentapp.R
import com.example.parliamentapp.database.comment.Comment
import timber.log.Timber

@BindingAdapter("party")
fun TextView.bindParties(party: String) {
    text = (when (party) {
        "kd" ->     "Kristillisdemokraatit"
        "kesk" ->   "Keskusta"
        "kok" ->    "Kokoomus"
        "liik" ->   "Liike Nyt"
        "ps" ->     "Perussuomalaiset"
        "r" ->      "Ruotsalainen kansanpuolue"
        "sd" ->     "Sosialidemokraatit"
        "vas" ->    "Vasemmisto"
        "vihr" ->   "Vihreät"
        else -> party
    })
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, picture: String?) {
    picture?.let {
        val imgUrl = "https://avoindata.eduskunta.fi/$picture"
        Timber.d(imgUrl)
        val imgUri = imgUrl.toUri()
            .buildUpon()
            .scheme("https")
            .build()
        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }
}

@BindingAdapter("commentDate")
fun TextView.setSleepQualityString(date: Long) {
    text = convertLongToDateString(date)
}