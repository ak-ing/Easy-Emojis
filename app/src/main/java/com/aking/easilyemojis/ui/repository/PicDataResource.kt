package com.aking.easilyemojis.ui.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aking.easilyemojis.bean.Pic
import com.aking.easilyemojis.ui.net.ApiService

/**
 * Created by Rick on 2022-11-29  13:32.
 * God bless my code!
 * Description: 图片列表paging
 */
class PicDataResource(private val service: ApiService, private val query: HashMap<String, String>) :
    PagingSource<Int, Pic>() {
    override fun getRefreshKey(state: PagingState<Int, Pic>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pic> {
        return try {
            val pageSize = params.loadSize
            val key = (params.key ?: 0)
            val start = if (key == 0) 0 else (key - 1) * pageSize + PicRepository.config.initialLoadSize
            query["limit"] = "$pageSize"
            query["start"] = "$start"
            Log.d("TAG", "pageSize: $pageSize  key  $key start $start")
            val items = service.getPics(query).data?.hit?.imgInfo?.item ?: emptyList()
            val prevKey = if (key > 0) key - 1 else null
            val nextKey = if (items.isNotEmpty()) key + 1 else null
            LoadResult.Page(items, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}