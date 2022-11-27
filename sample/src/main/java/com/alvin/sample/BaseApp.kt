package com.alvin.sample

import android.app.Application
import com.alvin.base_core.helper.GlobalUiEngine
import com.alvin.sample.base.CustomUiEngine

/**
 * <h3> 作用类描述：BaseApplication</h3>
 *
 * @Package :        com.alvin.sample
 * @Date :           2022/11/19
 * @author 高国峰
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalUiEngine.init(this, CustomUiEngine(), BuildConfig.DEBUG)
    }
}