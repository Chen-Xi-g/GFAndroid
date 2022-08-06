package com.alvin.base_core.base.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.alvin.base_core.helper.GlobalUIBuilder
import com.alvin.base_core.model.BarModel
import com.gyf.immersionbar.ktx.immersionBar

/**
 * <h3> 作用类描述：基础的Fragment设置</h3>
 *
 * @Package :        com.alvin.base_core.base.fragment
 * @Date :           2022/8/6
 * @author 高国峰
 */
abstract class BaseFragment : AbstractFragment() {
    /**
     * 获取默认的全局设置.
     */
    val iFragmentSetting by lazy(LazyThreadSafetyMode.NONE) {
        GlobalUIBuilder.iSettingBaseFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化 Bar
        initBar()
        // 初始化ViewBinding
        initBinding()
        //由具体的fragment实现，做视图相关的初始化
        initView(view, savedInstanceState)
        //由具体的fragment实现，做数据的初始化
        obtainData()
    }

    private fun initBar() {
        val barModel = setBar()
        immersionBar {
            if (barModel.statusColor == Color.TRANSPARENT) {
                transparentStatusBar()
            } else {
                statusBarColor(barModel.statusColor)
            }
            if (barModel.navigationColor == Color.TRANSPARENT) {
                transparentNavigationBar()
            } else {
                navigationBarColor(barModel.navigationColor)
            }
            statusBarDarkFont(barModel.isStatusDarkFont)
            navigationBarDarkIcon(barModel.isNavigationDarkIcon)
            init()
        }
    }

    /**
     * 初始化状态栏, 设置状态栏颜色或者透明
     */
    open fun setBar(): BarModel = iFragmentSetting.barModel()

    /**
     * 显示隐藏软键盘
     *
     * @param isOpen true 隐藏 false 显示
     */
    fun softInputHideOrShow(isOpen: Boolean) {
        if (activity?.currentFocus == null) return
        val inputManger =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (isOpen) inputManger?.showSoftInput(
            activity?.currentFocus,
            InputMethodManager.SHOW_FORCED
        ) else inputManger?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    /**
     * 内存不足 手动调用GC
     *
     */
    override fun onLowMemory() {
        System.gc()
        super.onLowMemory()
    }
}