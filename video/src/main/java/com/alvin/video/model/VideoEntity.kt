package com.alvin.video.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * <h3> 作用类描述：视频实体类</h3>
 *
 * @Package :        com.alvin.video.model
 * @Date :           2022/8/28
 * @author 高国峰
 *
 * @property url 视频地址
 * @property thumb 视频标题
 * @property width 视频封面宽度
 * @property height 视频封面高度
 */
@Parcelize
data class VideoEntity(
    val url: String,
    val thumb: String,
    val width: Int = -1,
    val height: Int = -1
) : Parcelable, IVideoEntity {
    override fun videoUrl(): String {
        return url
    }

    override fun videoTitle(): String {
        return ""
    }

    override fun videoThumbUrl(): String {
        return thumb
    }
}