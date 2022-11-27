package com.alvin.base_core.base.fragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.R
import com.alvin.base_core.helper.GlobalUiEngine

/**
 * <h3> 作用类描述：基础的函数声明</h3>
 *
 * @Package :        com.alvin.base_core.base.fragment
 * @Date :           2022/11/19
 * @author 高国峰
 */
abstract class BaseFragment : AbstractFragment() {


    /**
     * 全局UI配置
     */
    val uiEngine by lazy {
        setUiEngine()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化DataBinding
        initBinding()
        // 视图初始化
        initView(view, savedInstanceState)
        // 数据初始化
        obtainData()
    }

    /*局部属性设置*/

    /**
     * 局部UI配置
     *
     * @return IUiEngine
     */
    open fun setUiEngine() = GlobalUiEngine.uiEngine

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.rootLayout]
     */
    open fun rootLayout() = uiEngine.rootLayout()

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.rootLayoutListener]
     */
    open fun rootLayoutListener(activity: Activity, rootBinding: ViewDataBinding) =
        uiEngine.rootLayoutListener(activity, rootBinding)

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.titleLayout]
     */
    @LayoutRes
    open fun titleLayout(): Int = uiEngine.titleLayout()

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.titleLayoutIsShow]
     */
    open fun titleLayoutIsShow(): Boolean = uiEngine.titleLayoutIsShow()

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.titleLayoutListener]
     */
    open fun titleLayoutListener(activity: Activity, titleBinding: ViewDataBinding) =
        uiEngine.titleLayoutListener(activity, titleBinding)

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.loadingLayout]
     */
    @LayoutRes
    open fun loadingLayout(): Int = uiEngine.loadingLayout()

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.loadingLayoutListener]
     */
    open fun loadingLayoutListener(
        activity: Activity,
        loadingBinding: ViewDataBinding,
        msg: String
    ) =
        uiEngine.loadingLayoutListener(activity, loadingBinding, msg)

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.emptyLayout]
     */
    open fun emptyLayout(): Int = uiEngine.emptyLayout()

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.emptyLayoutListener]
     */
    open fun emptyLayoutListener(activity: Activity, emptyBinding: ViewDataBinding, msg: String) =
        uiEngine.emptyLayoutListener(activity, emptyBinding, msg)

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.errorLayout]
     */
    open fun errorLayout(): Int = uiEngine.errorLayout()

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.errorLayoutListener]
     */
    open fun errorLayoutListener(activity: Activity, errorBinding: ViewDataBinding, msg: String) =
        uiEngine.errorLayoutListener(activity, errorBinding, msg)

    /**
     * 重新加载
     */
    open fun onRetry() {

    }

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.loadingDialog]
     */
    open fun loadingDialog(
        activity: Activity,
        msg: String = getString(R.string.base_engine_str_loading)
    ) = uiEngine.loadingDialog(activity, msg)

    /**
     * 具体含义见[com.alvin.base_core.helper.IUiEngine.loadingDialogMsg]
     */
    open fun loadingDialogMsg(dialog: Dialog, msg: String) = uiEngine.loadingDialogMsg(dialog, msg)

}