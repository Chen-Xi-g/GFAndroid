package com.alvin.video.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.alvin.video.R
import com.alvin.video.model.ImageEngine
import com.alvin.video.model.VideoEntity
import com.alvin.video.utils.ProgressManagerImpl
import com.alvin.video.utils.Utils
import com.alvin.video.utils.VideoUtil
import xyz.doikki.videocontroller.StandardVideoController
import xyz.doikki.videocontroller.component.*
import xyz.doikki.videoplayer.controller.IControlComponent
import xyz.doikki.videoplayer.player.BaseVideoView
import xyz.doikki.videoplayer.player.VideoView

/**
 * <h3> 作用类描述：默认播放视频</h3>
 *
 * @Package :        com.yleanlink.common_ui.helper
 * @Date :           2022/8/11-17:05
 * @author 高国峰
 *
 * @property context Context
 * @property videoView VideoView 视频组件
 * @property isAutoPlay Boolean 是否自动播放
 * @property isOrientation Boolean 是否支持横竖屏切换
 * @property controlComponent 添加自定义控制组件
 */
class DefaultVideoHelp(
    private val context: Context,
    private val videoView: VideoView,
    private val isAutoPlay: Boolean = false,
    private val isOrientation: Boolean = false,
    private vararg val controlComponent: IControlComponent
) : LifecycleObserver {

    /**
     * 图片加载引擎
     */
    private var imageEngine: ImageEngine? = null

    /**
     * 视频视图控制器
     */
    private val mController by lazy {
        StandardVideoController(context)
    }

    /**
     * 准备播放界面
     */
    private val mPrepareView by lazy {
        PrepareView(context)
    }

    /**
     * 视频视图控制器
     */
    private val mErrorView by lazy {
        ErrorView(context)
    }

    /**
     * 视频视图控制器
     */
    private val mCompleteView by lazy {
        CompleteView(context)
    }

    /**
     * 视频视图控制器
     */
    private val mTitleView by lazy {
        TitleView(context)
    }

    private lateinit var entity: VideoEntity

    init {
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }
    }

    /**
     * 初始化视频播放器数据
     *
     * @param entity 视频实体
     */
    fun initData(entity: VideoEntity) {
        this.entity = entity
        initVideoView()
    }

    /**
     * 初始化视频播放参数
     *
     */
    private fun initVideoView() {
        videoView.setOnStateChangeListener(object : BaseVideoView.SimpleOnStateChangeListener() {
            override fun onPlayStateChanged(playState: Int) {
                super.onPlayStateChanged(playState)
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(videoView)
                }
            }
        })

        val proxyUrl = if (entity.videoUrl().contains(".m3u8")) {
            entity.videoUrl()
        } else {
            VideoUtil.cache().getProxyUrl(entity.videoUrl())
        }

        // 边播边缓存
        videoView.setUrl(proxyUrl)
        mTitleView.setTitle(entity.videoTitle())
        mTitleView.visibility = View.VISIBLE
        val thumb = mPrepareView.findViewById<ImageView>(R.id.thumb)
        thumb.layoutParams = FrameLayout.LayoutParams(entity.width, entity.height, Gravity.CENTER)
        imageEngine?.loadImage(context, entity.thumb, thumb)
        mPrepareView.setClickStart()
        mController.addControlComponent(mPrepareView)
        mController.addControlComponent(mTitleView)
        mController.addControlComponent(mErrorView)
        mController.addControlComponent(mCompleteView)
        mController.addControlComponent(VodControlView(context))
        mController.addControlComponent(GestureView(context))
        controlComponent.forEach {
            mController.addControlComponent(it)
        }
        mController.setEnableOrientation(isOrientation)
        videoView.setVideoController(mController)
        //保存进度
        videoView.setProgressManager(ProgressManagerImpl())

        if (isAutoPlay) {
            startPlay()
        }

    }

    /**
     * 开始播放
     */
    fun startPlay() {
        videoView.start()
    }

    /**
     * 释放视频播放
     *
     */
    @SuppressLint("SourceLockedOrientationActivity")
    private fun releaseVideoView() {
        videoView.release()
        if (videoView.isFullScreen) {
            videoView.stopFullScreen()
        }
        if (context is Activity) {
            if (context.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        } else if (context is Fragment) {
            if (context.activity?.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                context.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        videoView.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        videoView.resume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        releaseVideoView()
    }

    fun onBackPressed(): Boolean {
        return !videoView.onBackPressed()
    }

    fun setImageEngine(imageEngine: ImageEngine) {
        this.imageEngine = imageEngine
    }

}