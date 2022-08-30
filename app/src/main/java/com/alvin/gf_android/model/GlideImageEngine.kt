package com.alvin.gf_android.model

import android.content.Context
import android.widget.ImageView
import com.alvin.video.model.ImageEngine
import com.bumptech.glide.Glide

/**
 * <h3> 作用类描述：图片加载引擎</h3>
 *
 * @Package :        com.alvin.gf_android.model
 * @Date :           2022/8/30
 * @author 高国峰
 */
class GlideImageEngine : ImageEngine {
    override fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).into(imageView)
    }
}