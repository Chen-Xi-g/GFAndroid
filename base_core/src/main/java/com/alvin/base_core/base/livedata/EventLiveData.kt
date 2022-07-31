package com.alvin.base_core.base.livedata

/**
 * <h3> 作用类描述：包装类</h3>
 *
 * @Package :        com.yleanlink.mvvm.base.livedata
 * @Date :           2021/12/20-11:45
 * @author 高国峰
 */
open class EventLiveData<T>(isAllowNullValue: Boolean = true) :
    ProtectedUnPeekLiveData<T>(isAllowNullValue)