package com.aking.easilyemojis.ui.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.aking.easilyemojis.base.BaseRepository
import com.aking.easilyemojis.bean.PicData
import com.aking.easilyemojis.ui.net.ApiResponse
import com.aking.easilyemojis.ui.net.RetrofitClient

/**
 * Created by Rick at 2022/11/28 22:13
 * God bless my code.
 */
class PicRepository : BaseRepository() {

    companion object {

        private const val PAGE_SIZE = 10
        val config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = 5,
            initialLoadSize = 20,
            enablePlaceholders = false,
//            maxSize = PAGE_SIZE * 3
        )
    }


    private val mService by lazy { RetrofitClient.service }

    fun fetchPicsFromPaging(query: HashMap<String, String>) =
        Pager(config = config, pagingSourceFactory = { PicDataResource(mService, query) }).flow

    suspend fun fetchPicsFromNet(query: Map<String, String>): ApiResponse<PicData> {
        return executeHttp {
            mService.getPics(query)
        }
    }

}