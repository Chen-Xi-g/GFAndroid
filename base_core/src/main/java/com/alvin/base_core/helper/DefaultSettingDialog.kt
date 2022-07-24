package com.alvin.base_core.helper

import android.app.Activity

/**
 * <h3> 作用类描述：默认的Dialog设置</h3>
 *
 * <p> 0.0.1 版本中默认不进行Dialog设置, 这里可以自行替换第三方进行设置 </p>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/7/24
 * @author 高国峰
 */
class DefaultSettingDialog : IDialogSetting {
    override fun dialog(activity: Activity): Any? = null
    override fun show(dialog: Any, content: String) {
    }

    override fun dismiss(dialog: Any) {
    }

}