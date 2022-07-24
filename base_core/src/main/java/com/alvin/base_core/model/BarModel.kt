package com.alvin.base_core.model

import androidx.annotation.ColorRes
import com.alvin.base_core.R

/**
 * <h3> 作用类描述：设置状态栏和导航栏属性</h3>
 *
 * @Package :        com.alvin.base_core.model
 * @Date :           2022/7/23
 * @author 高国峰
 *
 * @param statusColor 状态栏颜色,默认为白色
 * @param isStatusDarkFont 是否使用深色字体,默认为true
 * @param navigationColor 导航条颜色,默认为白色
 * @param isNavigationDarkIcon 是否使用深色图标,默认为true
 */
data class BarModel(
    @ColorRes val statusColor: Int = R.color.gf_android_color_white,
    val isStatusDarkFont: Boolean = true,
    @ColorRes val navigationColor: Int = R.color.gf_android_color_white,
    val isNavigationDarkIcon: Boolean = true
)
