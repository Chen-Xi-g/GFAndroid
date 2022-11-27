package com.alvin.base_core.helper

import android.app.Activity
import android.app.Dialog
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.R
import com.alvin.base_core.base.dialog.DefaultLoadingDialog
import com.alvin.base_core.databinding.BaseEngineDefaultRootEmptyLayoutBinding
import com.alvin.base_core.databinding.BaseEngineDefaultRootErrorLayoutBinding
import com.alvin.base_core.databinding.BaseEngineDefaultRootLoadingLayoutBinding
import com.alvin.base_core.databinding.BaseEngineDefaultRootTitleLayoutBinding
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ClickUtils

/**
 * <h3> 作用类描述：默认全局UI配置</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/11/18
 * @author 高国峰
 */
class DefaultUiEngine(override val screenWidth: Int = 375) : IUiEngine {
    override fun setStatusBar(activity: Activity) {
        BarUtils.setStatusBarColor(
            activity,
            ContextCompat.getColor(activity, android.R.color.white)
        )
        BarUtils.setStatusBarLightMode(activity, true)
    }

    override fun rootLayout(): Int {
        return R.layout.base_engine_default_root_layout
    }

    override fun rootLayoutListener(activity: Activity, rootBinding: ViewDataBinding) {
    }

    override fun titleLayout(): Int {
        return R.layout.base_engine_default_root_title_layout
    }

    override fun titleLayoutIsShow(): Boolean {
        return true
    }

    override fun titleLayoutListener(activity: Activity, titleBinding: ViewDataBinding) {
        if (titleBinding is BaseEngineDefaultRootTitleLayoutBinding) {
            // 点击返回按钮，关闭当前Activity
            ClickUtils.applySingleDebouncing(titleBinding.baseEngineBack) {
                activity.finish()
            }
        }
    }

    override fun loadingLayout(): Int {
        return R.layout.base_engine_default_root_loading_layout
    }

    override fun loadingLayoutListener(
        activity: Activity,
        loadingBinding: ViewDataBinding,
        msg: String
    ) {
        if (loadingBinding is BaseEngineDefaultRootLoadingLayoutBinding) {
            loadingBinding.tvMsg.text = msg
        }
    }

    override fun emptyLayout(): Int {
        return R.layout.base_engine_default_root_empty_layout
    }

    override fun emptyLayoutListener(
        activity: Activity,
        emptyBinding: ViewDataBinding,
        msg: String
    ): Int {
        if (emptyBinding is BaseEngineDefaultRootEmptyLayoutBinding) {
            emptyBinding.tvMsg.text = msg
        }
        return R.id.btn_retry
    }

    override fun errorLayout(): Int {
        return R.layout.base_engine_default_root_error_layout
    }

    override fun errorLayoutListener(
        activity: Activity,
        errorBinding: ViewDataBinding,
        msg: String
    ): Int {
        if (errorBinding is BaseEngineDefaultRootErrorLayoutBinding) {
            errorBinding.tvMsg.text = msg
            // 点击重新加载
            ClickUtils.applySingleDebouncing(errorBinding.btnRetry) {
                // 重新加载
            }
        }
        return R.id.btn_retry
    }

    override fun loadingDialog(activity: Activity, msg: String): Dialog {
        return DefaultLoadingDialog(activity, msg)
    }

    override fun loadingDialogMsg(dialog: Dialog, msg: String) {
        if (dialog is DefaultLoadingDialog) {
            dialog.changeMsg(msg)
        }
    }

}