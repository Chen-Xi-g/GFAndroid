package com.alvin.gf_android

import android.app.Application
import com.alvin.base_core.helper.GlobalUIBuilder
import com.alvin.gf_android.model.GlideImageEngine
import com.alvin.gf_android.setting.AppSettingActivity
import com.alvin.video.utils.VideoUtil

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android
 * @Date :           2022/7/24
 * @author 高国峰
 */
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        GlobalUIBuilder.initSetting(AppSettingActivity())
        VideoUtil.init(this)
        VideoUtil.imageEngine = GlideImageEngine()
    }

}