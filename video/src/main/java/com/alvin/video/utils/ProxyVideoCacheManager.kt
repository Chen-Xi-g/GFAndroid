package com.alvin.video.utils

import android.content.Context
import com.danikula.videocache.HttpProxyCacheServer
import com.danikula.videocache.StorageUtils

object ProxyVideoCacheManager {
    private var sharedProxy: HttpProxyCacheServer? = null

    fun getProxy(context: Context): HttpProxyCacheServer {
        return if (sharedProxy == null) newProxy(context).also {
            sharedProxy = it
        } else sharedProxy!!
    }

    private fun newProxy(context: Context): HttpProxyCacheServer {
        return HttpProxyCacheServer.Builder(context)
            .maxCacheSize((512 * 1024 * 1024).toLong()) // 512MB for cache
            //缓存路径，不设置默认在sd_card/Android/data/[app_package_name]/cache中
            //                .cacheDirectory()
            .build()
    }

    /**
     * 删除所有缓存文件
     * @return 返回缓存是否删除成功
     */
    fun clearAllCache(context: Context): Boolean {
        getProxy(context)
        return StorageUtils.deleteFiles(sharedProxy!!.cacheRoot)
    }

    /**
     * 删除url对应默认缓存文件
     * @return 返回缓存是否删除成功
     */
    fun clearDefaultCache(context: Context, url: String?): Boolean {
        getProxy(context)
        val pathTmp = sharedProxy!!.getTempCacheFile(url)
        val path = sharedProxy!!.getCacheFile(url)
        return StorageUtils.deleteFile(pathTmp.absolutePath) &&
                StorageUtils.deleteFile(path.absolutePath)
    }
}