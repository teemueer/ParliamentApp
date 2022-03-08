/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Binding adapters called from resources in order to format values.
 * Glide is used for image caching when picture of a member is called.
 */

package com.example.parliamentapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.parliamentapp.R

// links abbreviations into party names
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

// caches images using Glide and shows spinner while loading
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, picture: String?) {
    picture?.let {
        val imgUrl = "https://avoindata.eduskunta.fi/$picture"
        val imgUri = imgUrl.toUri()
            .buildUpon()
            .scheme("https")
            .build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation))
            .into(imageView)
    }
}

// format comment dates nicely
@BindingAdapter("commentDate")
fun TextView.setCommentDate(date: Long) {
    text = convertLongToDateString(date)
}