package quiz.example.hd_wallpaper

import android.app.Application

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration


class application : Application() {

    override fun onCreate() {
        super.onCreate()

        val defaultOptions = DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .build()
        val config = ImageLoaderConfiguration.Builder(this)
            .defaultDisplayImageOptions(defaultOptions)
            .memoryCache(LruMemoryCache(20 * 1024 * 1024))
            .memoryCacheSize(20 * 1024 * 1024)
            .build()
        ImageLoader.getInstance().init(config)
    }


}