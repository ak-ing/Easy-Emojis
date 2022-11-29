package com.aking.easilyemojis.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/**
 * Created by Rick at 2022/11/29 21:15
 * God bless my code.
 */

@BindingAdapter("coilSrc", requireAll = false)
fun ImageView.coilSrc(url: String) {
    load(url)
}