package com.alvin.sample.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alvin.base_core.base.vm.BaseVM
import com.alvin.base_core.base.vm.UIState
import com.alvin.sample.entity.TestEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <h3> 作用类描述：演示VM更新缺省页</h3>
 *
 * @Package :        com.alvin.sample.vm
 * @Date :           2022/11/26
 * @author 高国峰
 */
class SampleVM : BaseVM() {

    /**
     * 模拟网络请求，回调数据
     */
    val testData = MutableLiveData<TestEntity>()

    /**
     * 成功的网络请求
     *
     * @param isLayout 显示加载布局还是Dialog，true为布局，false为Dialog
     */
    fun sendHttpSuccess(isLayout: Boolean = true) {
        viewModelScope.launch {
            // 网络请求中
            uiState.send(UIState.Loading("正在请求...", !isLayout))
            // 模拟请求两秒
            delay(2000)
            // 请求成功
            testData.value = TestEntity("请求成功标题", "请求成功标题")
            uiState.send(UIState.Success)
        }
    }

    /**
     * 失败的网络请求
     *
     * @param isLayout 显示错误布局还是Toast，true为布局，false为Toast
     */
    fun sendHttpError(isLayout: Boolean = true) {
        viewModelScope.launch {
            // 网络请求失败
            uiState.send(UIState.Loading("正在请求...", !isLayout))
            // 模拟请求两秒
            delay(2000)
            // 请求失败
            uiState.send(UIState.Error("请求失败", !isLayout))
        }
    }

    /**
     * 空数据的网络请求
     *
     * @param isLayout 显示空布局还是Toast，true为空布局，false显示Toast
     */
    fun sendHttpEmpty(isLayout: Boolean = true) {
        viewModelScope.launch {
            // 网络请求中
            uiState.send(UIState.Loading("正在请求...", !isLayout))
            // 模拟请求两秒
            delay(2000)
            // 请求成功，但是数据为空
            testData.value = TestEntity("", "")
            uiState.send(UIState.Empty("数据为空", !isLayout))
        }
    }
}