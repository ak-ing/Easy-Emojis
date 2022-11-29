package com.aking.easilyemojis.ui.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aking.easilyemojis.base.BaseViewModel
import com.aking.easilyemojis.ui.repository.PicRepository
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Rick at 2022/11/28 22:11
 * God bless my code.
 */
class HomeViewModel : BaseViewModel() {

    private val repository by lazy { PicRepository() }

    private val queryState = MutableStateFlow(hashMapOf("query" to "熊猫头"))

    val picPagingFlow = repository.fetchPicsFromPaging(queryState.value).cachedIn(viewModelScope)

    fun requestPic(query: HashMap<String, String>) {
        queryState.value = query
    }
}