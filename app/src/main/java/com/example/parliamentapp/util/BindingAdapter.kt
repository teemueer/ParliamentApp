package com.example.parliamentapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.parliamentapp.database.member.Member

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
