package com.aking.easilyemojis.ui.net

import com.aking.easilyemojis.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Rick at 2022/11/28 0:22
 * God bless my code.
 */
object RetrofitClient {
    private const val TIME_OUT = 5

    val service by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }

    private val client: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS).build()


    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

    fun <Service> getService(serviceClass: Class<Service>, baseUrl: String) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)

}