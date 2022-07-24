package com.alvin.gf_android

import android.app.Activity
import android.app.AlertDialog
import com.alvin.base_core.helper.IDialogSetting

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android
 * @Date :           2022/7/24
 * @author 高国峰
 */
class AppSettingDialog : IDialogSetting {
    override fun dialog(activity: Activity): Any? {
        return AlertDialog.Builder(activity).create()
    }

    override fun show(dialog: Any, content: String) {
        if (dialog is AlertDialog) {
            if (!dialog.isShowing) {
                dialog.setTitle("提示")
                dialog.setMessage(content)
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