package com.alvin.sample.base

import android.app.Activity
import android.app.Dialog
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.helper.IUiEngine
import com.alvin.sample.R
import com.alvin.sample.custom.CustomLoadingDialog
import com.alvin.sample.databinding.*
import com.blankj.utilcode.util.BarUtils

/**
 * <h3> 作用类描述：自定义全局UI配置</h3>
 *
 * @Package :        com.alvin.sample.base
 * @Date :           2022/11/22
 * @author 高国峰
 */
class CustomUiEngine(override val screenWidth: Int = 375) : IUiEngine {
    override fun setStatusBar(activity: Activity) {
        // 设置状态栏颜色
        BarUtils.setStatusBarColor(activity, ContextCompat.getColor(activity, R.color.blue))
        // 设置导航栏颜色
        BarUtils.setNavBarColor(activity, ContextCompat.getColor(activity, R.color.blue))
        // 设置状态栏图标颜色
        BarUtils.setStatusBarLightMode(activity, true)
        // 设置导航栏图标颜色
        BarUtils.setNavBarLightMode(activity, true)
    }

    override fun rootLayout(): Int {
        return R.layout.custom_root_layout
    }

    override fun rootLayoutListener(activity: Activity, rootBinding: ViewDataBinding) {
        // 判断是否是自定义的根布局
        if (rootBinding is CustomRootLayoutBinding) {
            rootBinding.tvTip.text = activity.javaClass.simpleName
        }
    }

    override fun titleLayout(): Int {
        return R.layout.custom_title_layout
    }

    override fun titleLayoutIsShow(): Boolean {
        // 不显示标题栏
//        return false
        // 显示标题栏
        return true
    }

    override fun titleLayoutListener(activity: Activity, titleBinding: ViewDataBinding) {
        // 判断是否是自定义的标题栏
        if (titleBinding is CustomTitleLayoutBinding) {
            titleBinding.ivBack.setOnClickListener {
                // 关闭页面
                activity.finish()
            }
        }
    }

    override fun loadingLayout(): Int {
        return R.layout.custom_loading_layout
    }

    override fun loadingLayoutListener(
        activity: Activity,
        loadingBinding: ViewDataBinding,
        msg: String
    ) {
        // 判断是否是自定义的加载布局
        if (loadingBinding is CustomLoadingLayoutBinding) {
            // 设置当前类名
            loadingBinding.tvClassName.text = activity.javaClass.simpleName
            // 设置加载提示
            loadingBinding.tvMsg.text = msg
        }
    }

    override fun loadingDialog(activity: Activity, msg: String): Dialog {
        return CustomLoadingDialog(activity, msg)
    }

    override fun loadingDialogMsg(dialog: Dialog, msg: String) {
        // 判断是否是自定义的加载框
        if (dialog is CustomLoadingDialog) {
            // 修改Dialog的提示
            dialog.changeMsg(msg)
        }
    }

    override fun emptyLayout(): Int {
        return R.layout.custom_empty_layout
    }

    override fun emptyLayoutListener(
        activity: Activity,
        emptyBinding: ViewDataBinding,
        msg: String
    ): Int {
        if (emptyBinding is CustomEmptyLayoutBinding) {
            // 设置当前类名
            emptyBinding.tvClassName.text = activity.javaClass.simpleName
            // 设置空数据提示
            emptyBinding.tvMsg.text = msg
        }
        // 返回重试View的ID
        return R.id.btn_retry
    }

    override fun errorLayout(): Int {
        return R.layout.custom_error_layout
    }

    override fun errorLayoutListener(
        activity: Activity,
        errorBinding: ViewDataBinding,
        msg: String
    ): Int {
        if (errorBinding is CustomErrorLayoutBinding) {
            // 设置当前类名
            errorBinding.tvClassName.text = activity.javaClass.simpleName
            // 设置错误提示
            errorBinding.tvMsg.text = msg
        }
        // 返回重试View的ID
        return R.id.btn_retry
    }
}