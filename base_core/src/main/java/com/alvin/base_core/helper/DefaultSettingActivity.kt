package com.alvin.base_core.helper

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.databinding.LayoutGfAndroidMvvmBaseTitleBinding
import com.alvin.base_core.model.BarModel

/**
 * <h3> 作用类描述：默认的Activity设置</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/7/23
 * @author 高国峰
 */
class DefaultSettingActivity : IActivitySetting {

    override fun barModel(): BarModel = BarModel()

    override fun isShowTitleLayout(): Boolean = true

    override fun titleLayoutClickListener(activity: Activity, titleLayoutBinding: ViewDataBinding) {
        if (titleLayoutBinding is LayoutGfAndroidMvvmBaseTitleBinding) {
            // 关闭Activity
            titleLayoutBinding.ibBack.setOnClickListener {
                activity.finish()
            }
        }
    }


}