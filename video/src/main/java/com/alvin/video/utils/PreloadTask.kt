package com.alvin.video.utils

import com.danikula.videocache.HttpProxyCacheServer
import xyz.doikki.videoplayer.util.L
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService

/**
 * 原理：主动去请求VideoCache生成的代理地址，触发VideoCache缓存机制
 * 缓存到 PreloadManager.PRELOAD_LENGTH 的数据之后停止请求，完成预加载
 * 播放器去播放VideoCache生成的代理地址的时候，VideoCache会直接返回缓存数据，
 * 从而提升播放速度
 */
class PreloadTask : Runnable {
    /**
     * 原始地址
     */
    var mRawUrl: String? = null

    /**
     * 列表中的位置
     */
    var mPosition = 0

    /**
     * VideoCache服务器
     */
    var mCacheServer: HttpProxyCacheServer? = null

    /**
     * 是否被取消
     */
    private var mIsCanceled = false

    /**
     * 是否正在预加载
     */
    private var mIsExecuted = false
    override fun run() {
        if (!mIsCanceled) {
            start()
        }
        mIsExecuted = false
        mIsCanceled = false
    }

    /**
     * 开始预加载
     */
    private fun start() {
        // 如果在小黑屋里不加载
        if (blackList.contains(mRawUrl)) return
        L.i("预加载开始：$mPosition")
        var connection: HttpURLConnection? = null
        try {
            //获取HttpProxyCacheServer的代理地址
            val proxyUrl = mCacheServer!!.getProxyUrl(mRawUrl)
            val url = URL(proxyUrl)
            connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            val `in`: InputStream = BufferedInputStream(connection.inputStream)
            var length: Int
            var read = -1
            val bytes = ByteArray(8 * 1024)
            while (`in`.read(bytes).also { length = it } != -1) {
                read += length
                //预加载完成或者取消预加载
                if (mIsCanceled || read >= PreloadManager.PRELOAD_LENGTH) {
                    if (mIsCanceled) {
                        L.i("预加载取消：$mPosition 读取数据：$read Byte")
                    } else {
                        L.i("预加载成功：$mPosition 读取数据：$read Byte")
                    }
                    break
                }
            }
        } catch (e: Exception) {
            L.i("预加载异常：" + mPosition + " 异常信息：" + e.message)
            // 关入小黑屋
            blackList.add(mRawUrl)
        } finally {
            connection?.disconnect()
            L.i("预加载结束: $mPosition")
        }
    }

    /**
     * 将预加载任务提交到线程池，准备执行
     */
    fun executeOn(executorService: ExecutorService) {
        if (mIsExecuted) return
        mIsExecuted = true
        executorService.submit(this)
    }

    /**
     * 取消预加载任务
     */
    fun cancel() {
        if (mIsExecuted) {
            mIsCanceled = true
        }
    }

    companion object {
        private val blackList: MutableList<String?> = ArrayList()
    }
}