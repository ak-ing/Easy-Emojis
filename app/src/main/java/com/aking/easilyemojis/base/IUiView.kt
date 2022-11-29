package com.aking.easilyemojis.base

import androidx.lifecycle.LifecycleOwner

/**
 * Created by Rick at 2022/11/27 20:25
 * God bless my code.
 */
interface IUiView : LifecycleOwner {
    fun showLoading()

    fun dismissLoading()

    fun getViewLifecycleOwner(): LifecycleOwner
}