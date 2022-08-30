package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alvin.gf_android.R
import com.alvin.gf_android.base.BaseGFActivity
import com.alvin.gf_android.databinding.ActivityDefVideoBinding
import com.alvin.gf_android.utils.CommonUIConstant
import com.alvin.gf_android.vm.DefVideoViewModel
import com.alvin.video.model.VideoEntity
import com.alvin.video.ui.VideoActivity

/**
 * <h3> 作用类描述：加载默认视频</h3>
 *
 * @Package :        com.alvin.gf_android.ui
 * @Date :           2022/8/30
 * @author 高国峰
 */
class DefVideoActivity :
    BaseGFActivity<DefVideoViewModel, ActivityDefVideoBinding>(R.layout.activity_def_video) {
    companion object {
        fun newIntent(context: Context) = Intent(context, DefVideoActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitleName("普通视频")
        binding.btnDef.setOnClickListener {
            VideoActivity.start(
                this, VideoEntity(
                    CommonUIConstant.videoList[1], CommonUIConstant.videoList[1],
                    -1, resources.getDimension(com.alvin.base_core.R.dimen.dp_720).toInt()
                )
            )
        }
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }
}