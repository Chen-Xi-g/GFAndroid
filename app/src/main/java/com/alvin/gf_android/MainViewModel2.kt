package com.alvin.gf_android

import androidx.lifecycle.viewModelScope
import com.alvin.base_core.base.livedata.EventLiveData
import com.alvin.base_core.base.view_model.BaseViewModel
import com.alvin.base_core.model.NetworkModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android
 * @Date :           2022/7/24
 * @author 高国峰
 */
class MainViewModel2 : BaseViewModel() {

    /**
     * TODO: 模拟网络请求
     */
    private val _testData by lazy(LazyThreadSafetyMode.NONE) {
        EventLiveData<List<String>>()
    }
    val testData: EventLiveData<List<String>> = _testData

    fun getSuccess() {
        viewModelScope.launch {
            httpCallback.beforeNetwork.value = NetworkModel()
            delay(3000)
            _testData.value = listOf("1", "2", "3")
            httpCallback.afterNetwork.value = true
        }
    }
}