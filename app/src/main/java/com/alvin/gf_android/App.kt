package com.alvin.gf_android

import android.app.Application
import com.alvin.base_core.helper.GlobalUIBuilder

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android
 * @Date :           2022/7/24
 * @author 高国峰
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalUIBuilder.initSetting(AppSettingActivity())
    }

}