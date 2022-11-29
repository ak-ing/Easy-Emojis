package com.aking.easilyemojis

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.CoilUtils
import okhttp3.OkHttpClient

class App : Application(), ImageLoaderFactory {

    override fun newImageLoader() = ImageLoader.Builder(this)
        .memoryCache(MemoryCache.Builder(this).maxSizePercent(0.25).build()).diskCache {
            DiskCache.Builder().directory(cacheDir.resolve("image_cache")).maxSizePercent(0.02)
                .build()
        }.components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.crossfade(true).build()

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
    }

    companion object {
        lateinit var instance: App
            private set
    }

    private inner class ApplicationLifecycleObserver : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            // TODO: "ApplicationObserver: app moved to foreground"
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            // TODO: "ApplicationObserver: app moved to background"
        }
    }

}