package com.alvin.gf_android.model

import com.alvin.video.model.IVideoEntity

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android.model
 * @Date :           2022/8/27
 * @author 高国峰
 */
data class TikTokEntityI(
    val videoUrl: String,
    val videoTitle: String,
    val videoThumbUrl: String
) : IVideoEntity {
    override fun videoUrl(): String {
        return videoUrl
    }

    override fun videoTitle(): String {
        return videoTitle
    }

    override fun videoThumbUrl(): String {
        return videoThumbUrl
    }
}