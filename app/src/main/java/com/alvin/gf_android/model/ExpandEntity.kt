package com.alvin.gf_android.model

import com.alvin.gfad.mode.ItemExpand

/**
 * <h3> 作用类描述：展开收起的ViewModel</h3>
 *
 * @Package :        com.alvin.gf_android.model
 * @Date :           2022/7/30
 * @author 高国峰
 */
data class ExpandEntity(
    val id: Int,
    val title: String,
    val content: String,
    override var itemGroupPosition: Int = 0,
    override var itemExpand: Boolean = false,
    override var itemChildList: List<Any?>?
) : ItemExpand