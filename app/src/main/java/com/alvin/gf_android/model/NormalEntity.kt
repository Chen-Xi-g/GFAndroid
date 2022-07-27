package com.alvin.gf_android.model

/**
 * <h3> 作用类描述：普通列表数据</h3>
 *
 * @Package :        com.alvin.gf_android.model
 * @Date :           2022/7/27
 * @author 高国峰
 *
 * @param id 唯一标识
 * @param title 标题
 * @param content 内容
 * @param imageUrl 图片地址
 */
data class NormalEntity(
    val id: Int,
    var title: String,
    var content: String,
    var imageUrl: String
)