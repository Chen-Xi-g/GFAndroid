package com.alvin.video.helper

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager2.widget.ViewPager2
import com.alvin.gfad.ReuseAdapter
import com.alvin.video.R
import com.alvin.video.model.IVideoEntity
import com.alvin.video.tiktok.TikTokController
import com.alvin.video.tiktok.TikTokRenderViewFactory
import com.alvin.video.tiktok.TikTokView
import com.alvin.video.utils.PreloadManager
import com.alvin.video.utils.Utils
import xyz.doikki.videoplayer.player.VideoView


/**
 * <h3> 作用类描述：模仿抖音的列表, 使用ViewPager2，建议直接使用[R.layout.alvin_include_tik_tok_view]作为视频播放布局/h3>
 *
 * @Package :        com.yleanlink.common_ui.helper
 * @Date :           2022/1/14-18:07
 * @author 高国峰
 *
 * @property context Context
 * @property viewPager2 ViewPager2
 * @property videoAdapter 适配器
 * @property commonPlayerContainer 视频容器Id
 * @property commonTikTok 视频控件Id
 */
class TikTokHelper(
    private val context: Context,
    private val viewPager2: ViewPager2,
    private val videoAdapter: ReuseAdapter,
    @IdRes private val commonTikTok: Int = R.id.commonTikTok,
    @IdRes private val commonPlayerContainer: Int = R.id.commonPlayerContainer,
) : LifecycleObserver {

    // 记录当前播放位置
    private var mCurPos = -1

    /**
     * 获取视频播放控件
     */
    private val mVideoView by lazy {
        VideoView(context)
    }

    /**
     * 视频视图控制器
     */
    private val mController by lazy {
        TikTokController(context)
    }

    /**
     * 预加载
     */
    private val mPreloadManager by lazy {
        PreloadManager.getInstance(context)
    }

    // 滑动到第几个索引时触发回调
    private var lastIndex: Int = -1

    // 回调
    private var onLoadDataListener: () -> Unit = {}

    init {
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }
        initPager()
        initVideoView()
    }

    private fun initPager() {
        viewPager2.adapter = videoAdapter
        viewPager2.offscreenPageLimit = 4
        viewPager2.overScrollMode = View.OVER_SCROLL_NEVER
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var mCurItem: Int = -1

            /**
             * VerticalViewPager是否反向滑动
             */
            private var mIsReverseScroll = false

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == mCurItem) {
                    return
                }
                mIsReverseScroll = position < mCurItem;
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == mCurPos) return
                viewPager2.post {
                    startPlay(position)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    mCurItem = viewPager2.currentItem
                }
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    mPreloadManager?.resumePreload(mCurPos, mIsReverseScroll)
                } else {
                    mPreloadManager?.pausePreload(mCurPos, mIsReverseScroll)
                }
            }
        })
    }


    /**
     * 初始化视频播放参数
     *
     */
    private fun initVideoView() {
        mVideoView.setLooping(true)
        mVideoView.setRenderViewFactory(TikTokRenderViewFactory.create())

        mVideoView.setVideoController(mController)
    }


    /**
     * 开始播放
     * @param position 列表位置
     */
    private fun startPlay(position: Int) {
        mVideoView.release()
        Utils.removeViewFormParent(mVideoView)
        val proxyUrl = when (val entity = videoAdapter.getData<Any>(position)) {

            is IVideoEntity -> {
                mPreloadManager?.getPlayUrl(entity.videoUrl()) ?: ""
            }
            is String -> {
                mPreloadManager?.getPlayUrl(entity) ?: ""
            }

            else -> throw UnsupportedOperationException(
                "实体类必须实现VideoEntity接口，重写videoUrl传入视频播放地址。或使用字符串" +
                        "(The entity class must implement the VideoEntity interface, overriding the videoUrl incoming video player address.)"
            )

        }
        // 边播边缓存
        mVideoView.setUrl(proxyUrl)

        val tikTok =
            videoAdapter.getView<TikTokView>(position, commonTikTok)
        val playerContainer =
            videoAdapter.getView(position, commonPlayerContainer) as? FrameLayout
                ?: throw UnsupportedOperationException("请检查构造参数commonPlayerContainer传入的ID值，如果使用include引入布局，传入include设置的ID。")
        mController.addControlComponent(tikTok, true)
        playerContainer.addView(mVideoView, 0)
        mVideoView.start()
        mCurPos = position
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun releaseVideoView() {
        mVideoView.release()
        if (mVideoView.isFullScreen) {
            mVideoView.stopFullScreen()
        }
        mCurPos = -1
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        mVideoView.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        mVideoView.resume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mVideoView.release()
    }

    fun onBackPressed(): Boolean {
        return mVideoView.onBackPressed()
    }

    /**
     * 加载新数据监听
     * @param lastIndex 滑动到倒数第几个索引触发回调
     */
    fun onLoadDataListener(lastIndex: Int, onLoadDataListener: () -> Unit) {
        this.lastIndex = lastIndex
        this.onLoadDataListener = onLoadDataListener
    }

}