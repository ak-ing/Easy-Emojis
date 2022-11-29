package com.aking.easilyemojis.ui.net

import com.aking.easilyemojis.bean.PicData
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Rick at 2022/11/28 21:18
 * God bless my code.
 */
interface ApiService {
    companion object {
        const val BASE_URL = "https://vt.sm.cn/api/pic/"
    }

    @GET("list")
    suspend fun getPics(@QueryMap query: Map<String, String>): ApiResponse<PicData>

}