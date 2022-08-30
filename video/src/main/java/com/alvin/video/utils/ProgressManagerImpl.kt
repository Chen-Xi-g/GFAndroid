package com.alvin.video.utils

import android.text.TextUtils
import android.util.LruCache
import xyz.doikki.videoplayer.player.ProgressManager

class ProgressManagerImpl : ProgressManager() {
    override fun saveProgress(url: String, progress: Long) {
        if (TextUtils.isEmpty(url)) return
        if (progress == 0L) {
            clearSavedProgressByUrl(url)
            return
        }
        mCache.put(url.hashCode(), progress)
    }

    override fun getSavedProgress(url: String): Long {
        return if (TextUtils.isEmpty(url)) 0 else mCache[url.hashCode()] ?: return 0
    }

    fun clearAllSavedProgress() {
        mCache.evictAll()
    }

    fun clearSavedProgressByUrl(url: String) {
        mCache.remove(url.hashCode())
    }

    companion object {
        //保存100条记录
        private val mCache = LruCache<Int, Long>(100)
    }
}