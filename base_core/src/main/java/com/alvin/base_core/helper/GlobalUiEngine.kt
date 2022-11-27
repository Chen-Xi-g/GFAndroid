package com.alvin.base_core.helper

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils

/**
 * <h3> 作用类描述：全局UI配置</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/11/18
 * @author 高国峰
 */
object GlobalUiEngine {

    var uiEngine: IUiEngine = DefaultUiEngine()

    /**
     * 实现[IUiEngine]初始化
     *
     * @param application Application
     * @param uiEngine IUiEngine 自定义UI配置
     * @param isDebug Boolean 是否是Debug模式
     */
    fun init(application: Application, uiEngine: IUiEngine, isDebug: Boolean = false) {
        this.uiEngine = uiEngine
        Utils.init(application)
        LogUtils.getConfig().isLogSwitch = isDebug
    }

}