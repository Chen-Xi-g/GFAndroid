package com.alvin.sample.custom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alvin.base_core.base.vm.BaseVM
import com.alvin.base_core.base.vm.UIState
import com.alvin.sample.entity.TestEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <h3> 作用类描述：默认的ViewModel</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class CustomVM : BaseVM() {
    val testData = MutableLiveData<TestEntity>()

    /**
     * 模拟网络请求，显示加载布局
     */
    fun sendHttpTestData() {
        viewModelScope.launch {
            uiState.send(UIState.Loading("自定义加载中..."))
            delay(2000)
            testData.value = TestEntity("网络请求的标题", "网络请求的内容")
            uiState.send(UIState.Success)
        }
    }

    /**
     * 模拟网络请求，显示空布局
     */
    fun sendHttpTestEmpty() {
        viewModelScope.launch {
            uiState.send(UIState.Loading("自定义加载中..."))
            delay(2000)
            uiState.send(UIState.Empty("数据加载为空"))
        }
    }

    /**
     * 模拟网络请求，显示错误布局
     */
    fun sendHttpTestError() {
        viewModelScope.launch {
            uiState.send(UIState.Loading("自定义加载中..."))
            delay(2000)
            uiState.send(UIState.Error("数据加载失败", false))
        }
    }

    /**
     * 模拟网络请求，显示加载Dialog
     */
    fun sendHttpTestDataDialog() {
        viewModelScope.launch {
            // Loading()中第二个参数为true时，显示Dialog
            uiState.send(UIState.Loading("上传第一张图片...", true))
            delay(2000)
            uiState.send(UIState.Loading("上传第二张图片...", true))
            delay(2000)
            testData.value = TestEntity("图片上传成功", "成功上传两张图片")
            uiState.send(UIState.Success)
        }
    }
}