package com.alvin.video.model

import android.content.Context
import android.widget.ImageView

/**
 * <h3> 作用类描述：图片加载引擎，自行设置图片</h3>
 *
 * @Package :        com.alvin.video.model
 * @Date :           2022/8/29
 * @author 高国峰
 */
interface ImageEngine {

    /**
     * 加载图片
     *
     * @param context Context
     * @param url String 图片地址
     * @param imageView ImageView 图片控件
     */
    fun loadImage(context: Context, url: String, imageView: ImageView)

}