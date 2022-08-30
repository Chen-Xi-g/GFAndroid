package com.alvin.video.utils

import android.content.Context
import com.alvin.video.utils.ProxyVideoCacheManager.getProxy
import com.danikula.videocache.HttpProxyCacheServer
import xyz.doikki.videoplayer.util.L
import java.util.concurrent.Executors

/**
 * 抖音预加载工具，使用AndroidVideoCache实现
 */
class PreloadManager private constructor(context: Context) {
    /**
     * 单线程池，按照添加顺序依次执行[PreloadTask]
     */
    private val mExecutorService = Executors.newSingleThreadExecutor()

    /**
     * 保存正在预加载的[PreloadTask]
     */
    private val mPreloadTasks = LinkedHashMap<String, PreloadTask>()

    /**
     * 标识是否需要预加载
     */
    private var mIsStartPreload = true
    private val mHttpProxyCacheServer: HttpProxyCacheServer = getProxy(context)

    /**
     * 开始预加载
     *
     * @param rawUrl 原始视频地址
     */
    fun addPreloadTask(rawUrl: String, position: Int) {
        if (isPreloaded(rawUrl)) return
        val task = PreloadTask()
        task.mRawUrl = rawUrl
        task.mPosition = position
        task.mCacheServer = mHttpProxyCacheServer
        L.i("addPreloadTask: $position")
        mPreloadTasks[rawUrl] = task
        if (mIsStartPreload) {
            //开始预加载
            task.executeOn(mExecutorService)
        }
    }

    /**
     * 判断该播放地址是否已经预加载
     */
    private fun isPreloaded(rawUrl: String): Boolean {
        //先判断是否有缓存文件，如果已经存在缓存文件，并且其大小大于1KB，则表示已经预加载完成了
        val cacheFile = mHttpProxyCacheServer.getCacheFile(rawUrl)
        if (cacheFile.exists()) {
            return if (cacheFile.length() >= 1024) {
                true
            } else {
                //这种情况一般是缓存出错，把缓存删掉，重新缓存
                cacheFile.delete()
                false
            }
        }
        //再判断是否有临时缓存文件，如果已经存在临时缓存文件，并且临时缓存文件超过了预加载大小，则表示已经预加载完成了
        val tempCacheFile = mHttpProxyCacheServer.getTempCacheFile(rawUrl)
        return if (tempCacheFile.exists()) {
            tempCacheFile.length() >= PRELOAD_LENGTH
        } else false
    }

    /**
     * 暂停预加载
     * 根据是否反向滑动取消在position之下或之上的PreloadTask
     *
     * @param position 当前滑到的位置
     * @param isReverseScroll 列表是否反向滑动
     */
    fun pausePreload(position: Int, isReverseScroll: Boolean) {
        L.d("pausePreload：$position isReverseScroll: $isReverseScroll")
        mIsStartPreload = false
        for ((_, task) in mPreloadTasks) {
            if (isReverseScroll) {
                if (task.mPosition >= position) {
                    task.cancel()
                }
            } else {
                if (task.mPosition <= position) {
                    task.cancel()
                }
            }
        }
    }

    /**
     * 恢复预加载
     * 根据是否反向滑动开始在position之下或之上的PreloadTask
     *
     * @param position        当前滑到的位置
     * @param isReverseScroll 列表是否反向滑动
     */
    fun resumePreload(position: Int, isReverseScroll: Boolean) {
        L.d("resumePreload：$position isReverseScroll: $isReverseScroll")
        mIsStartPreload = true
        for ((_, task) in mPreloadTasks) {
            if (isReverseScroll) {
                if (task.mPosition < position) {
                    if (!isPreloaded(task.mRawUrl ?: "")) {
                        task.executeOn(mExecutorService)
                    }
                }
            } else {
                if (task.mPosition > position) {
                    if (!isPreloaded(task.mRawUrl ?: "")) {
                        task.executeOn(mExecutorService)
                    }
                }
            }
        }
    }

    /**
     * 通过原始地址取消预加载
     *
     * @param rawUrl 原始地址
     */
    fun removePreloadTask(rawUrl: String) {
        val task = mPreloadTasks[rawUrl]
        if (task != null) {
            task.cancel()
            mPreloadTasks.remove(rawUrl)
        }
    }

    /**
     * 取消所有的预加载
     */
    fun removeAllPreloadTask() {
        val iterator: MutableIterator<Map.Entry<String, PreloadTask>> =
            mPreloadTasks.entries.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            val task = next.value
            task.cancel()
            iterator.remove()
        }
    }

    /**
     * 获取播放地址
     */
    fun getPlayUrl(rawUrl: String): String {
        val task = mPreloadTasks[rawUrl]
        task?.cancel()
        return if (isPreloaded(rawUrl)) {
            mHttpProxyCacheServer.getProxyUrl(rawUrl)
        } else {
            rawUrl
        }
    }

    companion object {
        private var sPreloadManager: PreloadManager? = null

        /**
         * 预加载的大小，每个视频预加载1M，这个参数可根据实际情况调整
         */
        const val PRELOAD_LENGTH = 1024 * 1024
        fun getInstance(context: Context): PreloadManager? {
            if (sPreloadManager == null) {
                synchronized(PreloadManager::class.java) {
                    if (sPreloadManager == null) {
                        sPreloadManager = PreloadManager(context.applicationContext)
                    }
                }
            }
            return sPreloadManager
        }
    }

}