package com.alvin.gf_android

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.databinding.LayoutGfAndroidMvvmBaseTitleBinding
import com.alvin.base_core.helper.IActivitySetting
import com.alvin.base_core.helper.IDialogSetting
import com.alvin.base_core.model.BarModel

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android
 * @Date :           2022/7/24
 * @author 高国峰
 */
class AppSettingActivity : IActivitySetting {
    override fun barModel(): BarModel = BarModel()

    override fun isShowTitleLayout(): Boolean = true

    override fun titleLayoutClickListener(activity: Activity, titleLayoutBinding: ViewDataBinding) {
        if (titleLayoutBinding is LayoutGfAndroidMvvmBaseTitleBinding) {
            titleLayoutBinding.ibBack.setOnClickListener {
                activity.finish()
            }
        }
    }

    override fun dialogSetting(): IDialogSetting {
        return AppSettingDialog()
    }
}