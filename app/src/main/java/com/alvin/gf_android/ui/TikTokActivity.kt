package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alvin.gf_android.R
import com.alvin.gf_android.base.BaseGFActivity
import com.alvin.gf_android.databinding.ActivityTikTokBinding
import com.alvin.gf_android.vm.TikTokViewModel
import com.alvin.video.model.VideoEntity
import com.alvin.video.ui.VideoPageActivity

/**
 * <h3> 作用类描述：抖音列表</h3>
 *
 * @Package :        com.alvin.gf_android.ui
 * @Date :           2022/8/27
 * @author 高国峰
 */
class TikTokActivity :
    BaseGFActivity<TikTokViewModel, ActivityTikTokBinding>(R.layout.activity_tik_tok) {

    companion object {
        fun newIntent(context: Context) = Intent(context, TikTokActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitleName("抖音列表")
    }

    override fun obtainData() {
        binding.btnTikTok.setOnClickListener {
            vm.getVideoList()
        }
    }

    override fun registerObserver() {
        vm.videoList.observe(this) {
            VideoPageActivity.start(
                this,
                it.map { map -> VideoEntity(map.videoUrl, map.videoThumbUrl) })
        }
    }
}