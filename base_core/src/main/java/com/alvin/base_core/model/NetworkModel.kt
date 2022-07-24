package com.alvin.base_core.model

import com.alvin.base_core.helper.GlobalUIBuilder

/**
 * <h3> 作用类描述：网络请求ViewModel实体类</h3>
 *
 * @Package :        com.alvin.base_core.model
 * @Date :           2022/7/24
 * @author 高国峰
 *
 * @param isShowDialog [com.alvin.base_core.base.BaseNetworkActivity.beforeNetwork]: true-> 显示Dialog加载框, false-> 不显示Dialog加载框;
 * @param isShowErrorLayout [com.alvin.base_core.base.BaseNetworkActivity.failed]: true-> 显示错误布局; false->显示Toast布局;
 * @param msg 显示的加载提示信息
 */
data class NetworkModel(
    val isShowDialog: Boolean = false,
    val isShowErrorLayout: Boolean = !GlobalUIBuilder.iSettingBaseActivity.isErrorToast(),
    val msg: String = "加载中..."
)