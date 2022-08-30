package com.alvin.video.model

/**
 * <h3> 作用类描述： 使用内置视频列表，必须实现的实体类接口</h3>
 *
 * @Package :        com.yleanlink.common_ui.model
 * @Date :           2022/1/18-16:08
 * @author 高国峰
 */
interface IVideoEntity {

    /**
     * 视频播放地址
     *
     * @return
     */
    fun videoUrl(): String

    /**
     * 视频标题地址
     *
     * @return
     */
    fun videoTitle(): String

    /**
     * 视频缩略图地址
     *
     * @return
     */
    fun videoThumbUrl(): String
}