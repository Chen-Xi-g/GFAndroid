package com.alvin.base_core.utils

import android.view.View
import com.blankj.utilcode.util.ClickUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ObjectUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * 显示Toast
 */
fun String?.toast() {
    if (isNullOrEmpty()) return
    if (length >= 10) {
        ToastUtils.showLong(this)
    } else {
        ToastUtils.showShort(this)
    }
}

/**
 * 点击事件防抖
 */
fun View.clickNotRepeat(block: (view: View) -> Unit) {
    ClickUtils.applySingleDebouncing(this, block)
}

/**
 * 显示Log
 */
fun Any?.logV() {
    if (ObjectUtils.isEmpty(this)) return
    LogUtils.v(this)
}

/**
 * 显示Log
 */
fun Any?.logD() {
    if (ObjectUtils.isEmpty(this)) return
    LogUtils.d(this)
}

/**
 * 显示Log
 */
fun Any?.logI() {
    if (ObjectUtils.isEmpty(this)) return
    LogUtils.i(this)
}

/**
 * 显示Log
 */
fun Any?.logW() {
    if (ObjectUtils.isEmpty(this)) return
    LogUtils.w(this)
}

/**
 * 显示Log
 */
fun Any?.logE() {
    if (ObjectUtils.isEmpty(this)) return
    LogUtils.e(this)
}