package com.aking.easilyemojis.base

import androidx.lifecycle.ViewModel

/**
 * Created by Rick at 2022/11/28 0:20
 * God bless my code.
 */
abstract class BaseViewModel : ViewModel()

sealed class ViewEffect {
    data class ShowLoading(val isShow: Boolean) : ViewEffect()
    data class ShowToast(val message: String) : ViewEffect()
    data class Success<T>(val data: T) : ViewEffect()
    object Empty : ViewEffect()
}