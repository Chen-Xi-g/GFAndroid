package com.alvin.gf_android.setting

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.helper.IActivitySetting
import com.alvin.base_core.helper.IDialogSetting
import com.alvin.base_core.model.BarModel
import com.alvin.gf_android.R
import com.alvin.gf_android.databinding.LayoutGlobalTitleBinding
import com.alvin.gf_android.databinding.LayoutLocalTitleBinding

/**
 * <h3> 作用类描述：自定义全局Activity属性</h3>
 *
 * @Package :        com.alvin.gf_android.setting
 * @Date :           2022/7/24
 * @author 高国峰
 */
class AppSettingActivity : IActivitySetting {
    override fun barModel(): BarModel = BarModel(0, true, android.R.color.transparent, false)

    override fun dialogSetting(): IDialogSetting {
        return AppSettingDialog()
    }

    override fun titleBarLayout(): Int {
        return R.layout.layout_global_title
    }

    override fun isShowTitleLayout(): Boolean = true

    override fun titleLayoutClickListener(activity: Activity, titleLayoutBinding: ViewDataBinding) {
        if (titleLayoutBinding is LayoutGlobalTitleBinding) {
            titleLayoutBinding.ibBack.setOnClickListener {
                activity.finish()
            }
        } else if (titleLayoutBinding is LayoutLocalTitleBinding) {
            titleLayoutBinding.ibLocalBack.setOnClickListener {
                activity.finish()
            }
        }
    }

    override fun loadingLayout(): Int = R.layout.layout_global_loading

    override fun errorLayout(): Int = R.layout.layout_global_error

    override fun errorRetryId(): Int = R.id.tvClickRetry

    override fun errorMessageId(): Int = R.id.tvError

    override fun isErrorToast(): Boolean = false

    override fun emptyLayout(): Int = R.layout.layout_global_empty
}