package com.alvin.base_core.base

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.R
import com.alvin.base_core.databinding.ActivityGfAndroidMvvmBaseBinding
import com.gyf.immersionbar.ktx.immersionBar

/**
 * <h3> 作用类描述：布局进初始化</h3>
 *
 * @Package :        com.alvin.base_core.base
 * @Date :           2022/7/24
 * @author 高国峰
 */
abstract class BaseLayoutActivity(@LayoutRes private val contentLayoutRes: Int) :
    BaseDialogActivity() {

    /**
     * 缺省页布局，携带Title
     */
    val baseRootLayout: ActivityGfAndroidMvvmBaseBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_gf_android_mvvm_base)
    }

    /**
     * 标题布局
     */
    private var titleLayout: ViewDataBinding? = null

    /**
     * 内容布局
     */
    private var contentLayout: ViewDataBinding? = null

    /**
     * 加载布局
     */
    private var loadingLayout: ViewDataBinding? = null

    /**
     * 错误布局
     */
    private var errorLayout: ViewDataBinding? = null

    /**
     * 空数据布局
     */
    private var emptyLayout: ViewDataBinding? = null

    /**
     * 解决错误信息重复弹窗的BUG
     */
    private val toast by lazy {
        setToast()
    }

    override fun initBinding() {
        baseRootLayout.lifecycleOwner = this
        initLayoutBinding()
    }

    /**
     * 初始化布局绑定操作
     */
    private fun initLayoutBinding() {
        loadTitleLayout()
        loadContentLayout()
        loadLoadingLayout()
        loadErrorLayout()
        loadEmptyLayout()
    }

    /**
     * 标题布局
     */
    private fun loadTitleLayout() {
        if (!isShowTitleLayout()) {
            baseRootLayout.baseTitleLayout.visibility = View.GONE
            return
        }
        baseRootLayout.baseTitleLayout.visibility = View.VISIBLE
        // 添加标题栏布局
        titleLayout = DataBindingUtil.inflate(
            layoutInflater,
            setTitleLayout(),
            baseRootLayout.baseTitleLayout,
            true
        )
        // 设置标题栏内边距
        immersionBar {
            titleBar(titleLayout?.root)
            init()
        }
        // 全局标题事件点击监听
        titleLayout?.let {
            it.lifecycleOwner = this
            iActivitySetting.titleLayoutClickListener(this, it)
        }
    }

    /**
     * 内容布局
     */
    private fun loadContentLayout() {
        // 添加内容布局
        contentLayout = DataBindingUtil.inflate(
            layoutInflater,
            contentLayoutRes,
            baseRootLayout.baseContentLayout,
            true
        )
        contentLayout?.lifecycleOwner = this
    }

    /**
     * 加载布局
     */
    private fun loadLoadingLayout() {
        // 添加加载布局
        loadingLayout = DataBindingUtil.inflate(
            layoutInflater,
            setLoadingLayout(),
            baseRootLayout.baseLoadingLayout,
            true
        )
        loadingLayout?.lifecycleOwner = this
    }

    /**
     * 错误布局
     */
    private fun loadErrorLayout() {
        // 添加错误布局
        errorLayout = DataBindingUtil.inflate(
            layoutInflater,
            setErrorLayout(),
            baseRootLayout.baseErrorLayout,
            true
        )
        // 错误布局点击监听
        errorLayout?.root?.findViewById<TextView>(setErrorRetryId())?.let {
            it.setOnClickListener {
                onErrorLayoutRetryClick()
            }
        }
        errorLayout?.lifecycleOwner = this
    }

    /**
     * 空数据布局
     */
    private fun loadEmptyLayout() {
        // 添加空数据布局
        emptyLayout = DataBindingUtil.inflate(
            layoutInflater,
            setEmptyLayout(),
            baseRootLayout.baseEmptyLayout,
            true
        )
        emptyLayout?.lifecycleOwner = this
    }

    /**
     * 是否显示标题布局
     *
     * @return Boolean true显示，false不显示
     */
    open fun isShowTitleLayout(): Boolean = iActivitySetting.isShowTitleLayout()

    /**
     * 错误布局重试点击监听
     */
    open fun onErrorLayoutRetryClick() {
        // 默认空实现
    }

    /**
     * @return Int 点击重试的ID
     */
    open fun setErrorRetryId() = iActivitySetting.errorRetryId()

    /**
     * 设置错误内容显示ID
     * @return Int
     */
    open fun setErrorMessageId() = iActivitySetting.errorMessageId()

    /**
     * 设置标题布局
     *
     * @return
     */
    @LayoutRes
    open fun setTitleLayout(): Int = iActivitySetting.titleBarLayout()

    /**
     * 设置加载布局
     *
     * @return
     */
    @LayoutRes
    open fun setLoadingLayout(): Int = iActivitySetting.loadingLayout()

    /**
     * 设置错误布局
     *
     * @return
     */
    @LayoutRes
    open fun setErrorLayout(): Int = iActivitySetting.errorLayout()

    /**
     * 设置空数据布局
     *
     * @return
     */
    @LayoutRes
    open fun setEmptyLayout(): Int = iActivitySetting.emptyLayout()

    /**
     * 获取标题布局
     *
     * @return T 指定类型的标题布局
     */
    fun <T : ViewDataBinding> getTitleLayout(): T {
        return titleLayout as? T
            ?: throw IllegalStateException("titleLayout is null,Please set the title layout through setTitleLayout.")
    }

    /**
     * 获取内容布局
     *
     * @return T 指定类型的内容布局
     */
    fun <T : ViewDataBinding> getContentLayout(): T {
        return contentLayout as? T
            ?: throw IllegalStateException("contentLayout is null,Please set the content layout through setContentLayout.")
    }

    /**
     * 获取加载布局
     */
    fun <T : ViewDataBinding> getLoadingLayout(): T {
        return loadingLayout as? T
            ?: throw IllegalStateException("loadingLayout is null,Please set the loading layout through setLoadingLayout.")
    }

    /**
     * 获取错误布局
     */
    fun <T : ViewDataBinding> getErrorLayout(): T {
        return errorLayout as? T
            ?: throw IllegalStateException("errorLayout is null,Please set the error layout through setErrorLayout.")
    }

    /**
     * 获取空数据布局
     */
    fun <T : ViewDataBinding> getEmptyLayout(): T {
        return emptyLayout as? T
            ?: throw IllegalStateException("emptyLayout is null,Please set the empty layout through setEmptyLayout.")
    }


    /**
     * 缺省页切换为内容布局
     */
    fun showContentLayout() {
        if (baseRootLayout.baseContentLayout.visibility == View.VISIBLE) return
        baseRootLayout.baseContentLayout.visibility = View.VISIBLE
        baseRootLayout.baseLoadingLayout.visibility = View.GONE
        baseRootLayout.baseErrorLayout.visibility = View.GONE
        baseRootLayout.baseEmptyLayout.visibility = View.GONE
    }

    /**
     * 缺省页切换为加载布局
     */
    fun showLoadingLayout() {
        if (baseRootLayout.baseLoadingLayout.visibility == View.VISIBLE) return
        baseRootLayout.baseLoadingLayout.visibility = View.VISIBLE
        baseRootLayout.baseContentLayout.visibility = View.GONE
        baseRootLayout.baseErrorLayout.visibility = View.GONE
        baseRootLayout.baseEmptyLayout.visibility = View.GONE
    }

    /**
     * 缺省页切换为错误布局
     */
    fun showErrorLayout(errorMsg: String = "") {
        baseRootLayout.baseErrorLayout.findViewById<TextView>(setErrorMessageId())?.text =
            errorMsg
        if (baseRootLayout.baseErrorLayout.visibility == View.VISIBLE) return
        baseRootLayout.baseErrorLayout.visibility = View.VISIBLE
        baseRootLayout.baseContentLayout.visibility = View.GONE
        baseRootLayout.baseLoadingLayout.visibility = View.GONE
        baseRootLayout.baseEmptyLayout.visibility = View.GONE
    }

    /**
     * 缺省页切换为错误布局
     */
    fun showErrorToast(errorMsg: String = "") {
        toast.setText(errorMsg)
        if (errorMsg.length >= 12) {
            toast.duration = Toast.LENGTH_LONG
        } else {
            toast.duration = Toast.LENGTH_SHORT
        }
        toast.show()
    }

    /**
     * 缺省页切换为空数据布局
     */
    fun showEmptyLayout() {
        if (baseRootLayout.baseEmptyLayout.visibility == View.VISIBLE) return
        baseRootLayout.baseEmptyLayout.visibility = View.VISIBLE
        baseRootLayout.baseContentLayout.visibility = View.GONE
        baseRootLayout.baseErrorLayout.visibility = View.GONE
        baseRootLayout.baseLoadingLayout.visibility = View.GONE
    }

    open fun setToast(): Toast {
        return Toast.makeText(this, "", Toast.LENGTH_SHORT)
    }

    override fun onStop() {
        super.onStop()
        toast.cancel()
    }

}