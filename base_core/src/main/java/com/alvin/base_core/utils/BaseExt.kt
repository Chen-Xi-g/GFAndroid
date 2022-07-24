package com.alvin.base_core.utils

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * TextView跑马灯效果
 *
 * @param enable 是否开启跑马灯效果
 */
@BindingAdapter("horizontallyTextScroll")
fun TextView.horizontallyTextScroll(enable: Boolean) {
    if (!enable) return
    ellipsize = TextUtils.TruncateAt.MARQUEE
    isSingleLine = true
    isSelected = true
    isFocusable = true
    isFocusableInTouchMode = true
    marqueeRepeatLimit = -1
}