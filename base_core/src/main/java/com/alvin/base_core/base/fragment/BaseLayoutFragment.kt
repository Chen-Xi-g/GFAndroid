package com.alvin.base_core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.R
import com.alvin.base_core.databinding.LayoutGfAndroidMvvmBaseBinding
import com.gyf.immersionbar.ktx.immersionBar

/**
 * <h3> 作用类描述：布局进初始化</h3>
 *
 * @Package :        com.alvin.base_core.base.fragment
 * @Date :           2022/8/6
 * @author 高国峰
 *
 * @param contentLayoutRes 内容布局资源id
 */
abstract class BaseLayoutFragment(@LayoutRes private val contentLayoutRes: Int) :
    BaseDialogFragment() {
    /**
     * 缺省页布局，携带Title
     */
    lateinit var baseRootLayout: LayoutGfAndroidMvvmBaseBinding

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseRootLayout =
            DataBindingUtil.inflate(
                inflater,
                R.layout.layout_gf_android_mvvm_base,
                container,
                false
            )
        baseRootLayout.lifecycleOwner = this
        return baseRootLayout.root
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
        if (!isShowTitleLayout() || setTitleLayout() == -1) {
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
            iFragmentSetting.titleLayoutClickListener(this, it)
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
        if (setLoadingLayout() == -1) {
            baseRootLayout.baseLoadingLayout.visibility = View.GONE
            return
        }
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
        if (setErrorLayout() == -1) {
            baseRootLayout.baseErrorLayout.visibility = View.GONE
            return
        }
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
        if (setEmptyLayout() == -1) {
            baseRootLayout.baseEmptyLayout.visibility = View.GONE
            return
        }
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
    open fun isShowTitleLayout(): Boolean = iFragmentSetting.isShowTitleLayout()

    /**
     * 错误布局重试点击监听
     */
    open fun onErrorLayoutRetryClick() {
        // 默认空实现
    }

    /**
     * @return Int 点击重试的ID
     */
    open fun setErrorRetryId() = iFragmentSetting.errorRetryId()

    /**
     * 设置错误内容显示ID
     * @return Int
     */
    open fun setErrorMessageId() = iFragmentSetting.errorMessageId()

    /**
     * 设置标题布局
     *
     * @return 当值为-1时，不显示标题布局
     */
    @LayoutRes
    open fun setTitleLayout(): Int = iFragmentSetting.titleBarLayout()

    /**
     * 设置加载布局
     *
     * @return 当值为-1时，不显示加载布局
     */
    @LayoutRes
    open fun setLoadingLayout(): Int = iFragmentSetting.loadingLayout()

    /**
     * 设置错误布局
     *
     * @return 当值为-1时，不显示错误布局
     */
    @LayoutRes
    open fun setErrorLayout(): Int = iFragmentSetting.errorLayout()

    /**
     * 设置空数据布局
     *
     * @return 当值为-1时，不显示空数据布局
     */
    @LayoutRes
    open fun setEmptyLayout(): Int = iFragmentSetting.emptyLayout()

    /**
     * 获取标题布局
     *
     * @return T 指定类型的标题布局
     */
    fun <T : ViewDataBinding> getTitleLayout(): T {
        if (setTitleLayout() == -1) {
            throw IllegalArgumentException("请设置标题布局")
        }
        return titleLayout as? T
            ?: throw IllegalStateException("titleLayout is null,Please set the title layout through setTitleLayout.")
    }

    /**
     * 获取内容布局
     *
     * @return T 指定类型的内容布局
     */
    fun <T : ViewDataBinding> getContentLayout(): T {
        if (contentLayoutRes == -1) {
            throw IllegalArgumentException("请设置内容布局")
        }
        return contentLayout as? T
            ?: throw IllegalStateException("contentLayout is null,Please set the content layout through setContentLayout.")
    }

    /**
     * 获取加载布局
     */
    fun <T : ViewDataBinding> getLoadingLayout(): T {
        if (setLoadingLayout() == -1) {
            throw IllegalArgumentException("请设置加载布局")
        }
        return loadingLayout as? T
            ?: throw IllegalStateException("loadingLayout is null,Please set the loading layout through setLoadingLayout.")
    }

    /**
     * 获取错误布局
     */
    fun <T : ViewDataBinding> getErrorLayout(): T {
        if (setErrorLayout() == -1) {
            throw IllegalArgumentException("请设置错误布局")
        }
        return errorLayout as? T
            ?: throw IllegalStateException("errorLayout is null,Please set the error layout through setErrorLayout.")
    }

    /**
     * 获取空数据布局
     */
    fun <T : ViewDataBinding> getEmptyLayout(): T {
        if (setEmptyLayout() == -1) {
            throw IllegalArgumentException("请设置空数据布局")
        }
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
        if (baseRootLayout.baseLoadingLayout.visibility == View.VISIBLE || setLoadingLayout() == -1) return
        baseRootLayout.baseLoadingLayout.visibility = View.VISIBLE
        baseRootLayout.baseContentLayout.visibility = View.GONE
        baseRootLayout.baseErrorLayout.visibility = View.GONE
        baseRootLayout.baseEmptyLayout.visibility = View.GONE
    }

    /**
     * 缺省页切换为错误布局
     */
    fun showErrorLayout(errorMsg: String = "") {
        if (setErrorLayout() == -1) return
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
        if (baseRootLayout.baseEmptyLayout.visibility == View.VISIBLE || setEmptyLayout() == -1) return
        baseRootLayout.baseEmptyLayout.visibility = View.VISIBLE
        baseRootLayout.baseContentLayout.visibility = View.GONE
        baseRootLayout.baseErrorLayout.visibility = View.GONE
        baseRootLayout.baseLoadingLayout.visibility = View.GONE
    }

    open fun setToast(): Toast {
        return Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT)
    }

    override fun onStop() {
        super.onStop()
        toast.cancel()
    }
}