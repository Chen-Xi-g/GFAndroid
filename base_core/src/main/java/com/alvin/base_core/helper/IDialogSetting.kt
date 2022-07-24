package com.alvin.base_core.helper

import android.app.Activity

/**
 * <h3> 作用类描述：设置Dialog默认的相关属性</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/7/24
 * @author 高国峰
 *
 * @param T Dialog泛型
 */
interface IDialogSetting {

    /**
     * 设置Dialog加载控件
     *
     * @return Any 返回自定义的Dialog加载控件
     */
    fun dialog(activity: Activity): Any?

    /**
     * 显示Dialog
     */
    fun show(dialog: Any, content: String)

    /**
     * 关闭Dialog
     */
    fun dismiss(dialog: Any)

}