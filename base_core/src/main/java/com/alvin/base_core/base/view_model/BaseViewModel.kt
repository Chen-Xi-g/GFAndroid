package com.alvin.base_core.base.view_model

import androidx.lifecycle.ViewModel
import com.alvin.base_core.base.livedata.BooleanLiveData
import com.alvin.base_core.base.livedata.EventLiveData
import com.alvin.base_core.model.NetworkModel

/**
 * <h3> 作用类描述：所有ViewModel的基类</h3>
 *
 * @Package :        com.alvin.base_core.base.view_model
 * @Date :           2022/7/24
 * @author 高国峰
 */
open class BaseViewModel : ViewModel() {

    /**
     * 在Activity中处理请求结果
     */
    val httpCallback by lazy { HttpCallback() }

    inner class HttpCallback {

        /**
         * 开始请求数据
         */
        val beforeNetwork by lazy { EventLiveData<NetworkModel>() }

        /**
         * 请求发生错误
         */
        val failed by lazy { EventLiveData<NetworkModel>() }

        /**
         * 请求结束
         *
         * true: 显示内容布局
         * false: 显示空数据布局
         */
        val afterNetwork by lazy { BooleanLiveData() }

    }

}