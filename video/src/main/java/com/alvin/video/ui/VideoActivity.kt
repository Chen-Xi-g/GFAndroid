package com.alvin.video.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alvin.video.R
import com.alvin.video.databinding.AlvinActivityVideoBinding
import com.alvin.video.helper.DefaultVideoHelp
import com.alvin.video.model.VideoEntity
import com.alvin.video.utils.VideoUtil

/**
 * <h3> 作用类描述：普通视频播放</h3>
 *
 * @Package :        com.alvin.video.ui
 * @Date :           2022/8/30
 * @author 高国峰
 */
class VideoActivity : AppCompatActivity() {
    companion object {
        /**
         * 初始化跳转
         *
         * @param entity VideoEntity 视频实体类
         */
        fun start(activity: Activity, entity: VideoEntity) {
            Intent(activity, VideoActivity::class.java).apply {
                putExtra("entity", entity)
                activity.startActivity(this)
            }
        }
    }

    /**
     * 视频数据
     */
    private lateinit var entity: VideoEntity

    /**
     * 抖音视频辅助类
     */
    private var defaultVideoHelp: DefaultVideoHelp? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        window.navigationBarColor = Color.TRANSPARENT;
        window.statusBarColor = Color.TRANSPARENT;
        entity = intent.getParcelableExtra("entity") ?: throw IllegalArgumentException(
            "entity is null"
        )
        // 初始化DataBinding
        val binding = DataBindingUtil.setContentView<AlvinActivityVideoBinding>(
            this,
            R.layout.alvin_activity_video
        )
        defaultVideoHelp = DefaultVideoHelp(
            this,
            binding.dkVideoView
        )
        VideoUtil.imageEngine?.let {
            defaultVideoHelp?.setImageEngine(it)
        }
        defaultVideoHelp?.initData(entity)
    }
}