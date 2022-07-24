package com.alvin.base_core.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.alvin.base_core.helper.GlobalUIBuilder
import com.alvin.base_core.model.BarModel
import com.gyf.immersionbar.ktx.immersionBar


/**
 * <h3> 作用类描述：基础的Activity设置</h3>
 *
 * @Package :        com.alvin.base_core.base
 * @Date :           2022/7/23
 * @author 高国峰
 */
abstract class BaseActivity : AbstractActivity() {

    /**
     * 获取默认的全局设置.
     */
    val iActivitySetting by lazy(LazyThreadSafetyMode.NONE) {
        GlobalUIBuilder.iSettingBaseActivity
    }

    /**
     * 当前Activity是否可见.
     */
    var isResume = false
        private set

    /**
     * 当前Activity是否销毁.
     */
    var isDestroy = false
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 Bar
        initBar()
        // 初始化ViewBinding
        initBinding()
        //由具体的activity实现，做视图相关的初始化
        initView(savedInstanceState)
        //由具体的activity实现，做数据的初始化
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
    open fun setBar(): BarModel = iActivitySetting.barModel()

    /**
     * 点击空白区域关闭软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) ev.let {
            if (isShouldHideInput(currentFocus, it)) {
                val im =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(
                    currentFocus?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isShouldHideInput(view: View?, ev: MotionEvent): Boolean {
        if (view is EditText) {
            val l: IntArray = intArrayOf(0, 0)
            view.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom: Int = top + view.getHeight()
            val right: Int = (left
                    + view.getWidth())
            return !(ev.x > left && ev.x < right
                    && ev.y > top && ev.y < bottom)
        }
        return false
    }

    /**
     * 显示隐藏软键盘
     *
     * @param isOpen true 隐藏 false 显示
     */
    fun softInputHideOrShow(isOpen: Boolean) {
        if (currentFocus == null) return
        val inputManger =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (isOpen) inputManger.showSoftInput(
            currentFocus,
            InputMethodManager.SHOW_FORCED
        ) else inputManger.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        isResume = true
    }

    override fun onPause() {
        super.onPause()
        isResume = false
    }

    override fun onDestroy() {
        isDestroy = true
        super.onDestroy()
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