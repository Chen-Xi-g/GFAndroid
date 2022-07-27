package com.alvin.gf_android.setting

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.alvin.base_core.helper.IDialogSetting

/**
 * <h3> 作用类描述：局部Dialog设置</h3>
 *
 * @Package :        com.alvin.gf_android.setting
 * @Date :           2022/7/27
 * @author 高国峰
 */
class LocalSettingDialog : IDialogSetting {
    override fun dialog(activity: Activity): Any {
        return AlertDialog.Builder(activity)
            .setTitle("局部提示")
            .create()
    }

    override fun show(dialog: Any, content: String) {
        if (dialog is AlertDialog) {
            dialog.setMessage(content)
            if (!dialog.isShowing) {
                dialog.show()
            }
        }
    }

    override fun dismiss(dialog: Any) {
        if (dialog is AlertDialog) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
    }
}