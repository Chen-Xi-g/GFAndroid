package com.alvin.base_core.base.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel

/**
 * <h3> 作用类描述：所有ViewModel需要继承的类</h3>
 *
 * @Package :        com.alvin.base_core.vm
 * @Date :           2022/11/17
 * @author 高国峰
 */
abstract class BaseVM : ViewModel() {

    /**
     * UI状态
     */
    val uiState = Channel<UIState>(Channel.UNLIMITED)

}

/**
 * BaseUI缺省页
 */
sealed class UIState {

    /**
     * 加载中
     *
     * @param msg String 提示信息
     * @param isDialog Boolean 是否是Dialog, 如果为true则显示Dialog, 否则显示缺省页
     */
    data class Loading(
        val msg: String,
        val isDialog: Boolean = false
    ) : UIState()

    /**
     * 加载数据为空
     *
     * @param msg 空数据提示信息
     * @param isToast 是否显示Toast，如果为true则显示Toast，否则显示缺省页
     */
    data class Empty(
        val msg: String,
        val isToast: Boolean = false
    ) : UIState()

    /**
     * 加载失败
     *
     * @param msg 失败提示信息
     * @param isToast 是否显示Toast，如果为true则显示Toast，否则显示缺省页
     */
    data class Error(
        val msg: String,
        val isToast: Boolean = true
    ) : UIState()

    /**
     * 加载成功，隐藏缺省页
     */
    object Success : UIState()
}