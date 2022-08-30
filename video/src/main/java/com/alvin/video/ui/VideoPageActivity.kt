package com.alvin.video.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.alvin.gfad.utils.reuseAdapter
import com.alvin.gfad.utils.setup
import com.alvin.video.R
import com.alvin.video.databinding.AlvinActivityVideoPageBinding
import com.alvin.video.helper.TikTokHelper
import com.alvin.video.model.VideoEntity
import com.alvin.video.tiktok.TikTokView
import com.bumptech.glide.Glide

/**
 * <h3> 作用类描述：播放视频的Activity</h3>
 *
 * @Package :        com.alvin.video.ui
 * @Date :           2022/8/28
 * @author 高国峰
 */
class VideoPageActivity : AppCompatActivity() {

    companion object {
        /**
         * 初始化跳转
         *
         * @param list List<String>
         */
        fun start(activity: Activity, list: List<VideoEntity>) {
            Intent(activity, VideoPageActivity::class.java).apply {
                putParcelableArrayListExtra("list", ArrayList(list))
                activity.startActivity(this)
            }
        }
    }

    /**
     * 视频数据
     */
    private lateinit var list: ArrayList<VideoEntity>

    /**
     * 抖音视频辅助类
     */
    private var tiktokHelper: TikTokHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        window.navigationBarColor = Color.TRANSPARENT;
        window.statusBarColor = Color.TRANSPARENT;
        // 获取传递过来的数据
        list = intent.getParcelableArrayListExtra("list") ?: ArrayList()
        // 初始化DataBinding
        val binding = DataBindingUtil.setContentView<AlvinActivityVideoPageBinding>(
            this,
            R.layout.alvin_activity_video_page
        )
        binding.vp2.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.vp2.setup {
            addType<VideoEntity>(R.layout.alvin_item_tik_tok)
            onBind {
                val item = getItem<VideoEntity>()
                val tikTokView = findView<TikTokView>(R.id.commonTikTok)
                val thumb = tikTokView.findViewById<ImageView>(R.id.thumb)
                thumb.layoutParams =
                    FrameLayout.LayoutParams(item.width, item.height, Gravity.CENTER)
                Glide.with(this@VideoPageActivity).load(item.thumb).into(thumb)
            }
        }
        tiktokHelper = TikTokHelper(this, binding.vp2, binding.vp2.reuseAdapter)
        binding.vp2.reuseAdapter.setData(list.toList())
    }

}