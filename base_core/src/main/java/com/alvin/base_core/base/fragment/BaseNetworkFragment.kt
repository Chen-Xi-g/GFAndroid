package com.alvin.base_core.base.fragment

import androidx.annotation.LayoutRes
import com.alvin.base_core.model.NetworkModel

/**
 * <h3> 作用类描述：网络回调</h3>
 *
 * @Package :        com.alvin.base_core.base.fragment
 * @Date :           2022/8/6
 * @author 高国峰
 */
abstract class BaseNetworkFragment(@LayoutRes private val contentLayoutRes: Int) :
    BaseLayoutFragment(contentLayoutRes) {
    /**
     * 网络请求开始
     *
     * @param networkModel NetworkModel 网络请求ViewModel实体类
     */
    open fun beforeNetwork(networkModel: NetworkModel) {
        if (networkModel.isShowDialog) {
            loading(networkModel.msg)
        } else {
            showLoadingLayout()
        }
    }

    /**
     * 网络请求结束
     *
     * @param isNotEmpty 不是空数据? true: 显示内容布局，false: 显示空布局
     */
    open fun afterNetwork(isNotEmpty: Boolean) {
        if (isNotEmpty) {
            showContentLayout()
        } else {
            showEmptyLayout()
        }
        dismiss()
    }

    /**
     * 网络请求失败
     *
     * @param networkModel NetworkModel 网络请求ViewModel实体类
     */
    open fun failed(networkModel: NetworkModel) {
        if (networkModel.isShowErrorLayout) {
            showErrorLayout(networkModel.msg)
        } else {
            showErrorToast(networkModel.msg)
        }
        dismiss()
    }
}