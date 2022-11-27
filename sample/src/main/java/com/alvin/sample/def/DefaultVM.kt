package com.alvin.sample.def

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alvin.base_core.base.vm.BaseVM
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
class DefaultVM : BaseVM() {
    val testData = MutableLiveData<TestEntity>()

    /**
     * 模拟网络请求
     */
    fun sendHttpTestData() {
        viewModelScope.launch {
            delay(2000)
            testData.value = TestEntity("网络请求的标题", "网络请求的内容")
        }
    }
}