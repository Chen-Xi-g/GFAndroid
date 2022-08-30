package com.alvin.video.utils

import android.app.Application
import com.alvin.video.model.ImageEngine
import com.danikula.videocache.HttpProxyCacheServer
import xyz.doikki.videoplayer.exo.ExoMediaPlayerFactory
import xyz.doikki.videoplayer.player.VideoViewConfig
import xyz.doikki.videoplayer.player.VideoViewManager

/**
 * <h3> 作用类描述：初始视频控件</h3>
 *
 * @Package :        com.yleanlink.common_ui.utils
 * @Date :           2022/1/17-10:56
 * @author 高国峰
 */
object VideoUtil {

    private lateinit var application: Application

    var imageEngine: ImageEngine? = null

    fun init(application: Application) {
        this.application = application
        // 播放器默认使用Exo
        VideoViewManager.setConfig(
            VideoViewConfig.newBuilder()
                .setPlayerFactory(ExoMediaPlayerFactory.create())
                .build()
        )
    }

    fun cache(): HttpProxyCacheServer {
        return ProxyVideoCacheManager.getProxy(application)
    }
}