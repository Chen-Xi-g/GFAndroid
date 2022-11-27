package com.alvin.base_core.base.activity

import android.app.Dialog
import android.content.res.Configuration
import android.content.res.Resources
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.R
import com.alvin.base_core.utils.toast
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ClickUtils


/**
 * <h3> 作用类描述：设置Activity内容</h3>
 *
 * @Package :        com.alvin.base_core.base.activity
 * @Date :           2022/11/18
 * @author 高国峰
 */
abstract class BaseContentActivity<DB : ViewDataBinding>(
    @LayoutRes private val contentLayoutId: Int
) : BaseActivity() {

    /**
     * 缺省页布局，携带Title
     */
    val rootBinding: ViewDataBinding by lazy {
        DataBindingUtil.setContentView(this, rootLayout())
    }
    private val rootTitleLayout by lazy {
        rootBinding.root.findViewById<FrameLayout>(R.id.root_title)
    }
    private val rootContentLayout by lazy {
        rootBinding.root.findViewById<FrameLayout>(R.id.root_content)
    }

    /**
     * 标题布局
     */
    private var _titleLayout: ViewDataBinding? = null

    /**
     * 内容布局
     */
    private var _contentLayout: DB? = null

    /**
     * 加载布局
     */
    private var _loadingLayout: ViewDataBinding? = null

    /**
     * 错误布局
     */
    private var _errorLayout: ViewDataBinding? = null

    /**
     * 空数据布局
     */
    private var _emptyLayout: ViewDataBinding? = null

    /**
     * Loading Dialog
     */
    private var _loadingDialog: Dialog? = null

    override fun initBinding() {
        rootLayoutListener(this, rootBinding)
        loadTitleLayout()
        loadContentLayout()
    }

    /**
     * 标题布局
     */
    private fun loadTitleLayout() {
        if (!titleLayoutIsShow() || titleLayout() == -1) {
            rootTitleLayout.visibility = View.GONE
            return
        }
        rootTitleLayout.visibility = View.VISIBLE
        // 添加标题栏布局
        _titleLayout = DataBindingUtil.inflate(
            layoutInflater,
            titleLayout(),
            rootTitleLayout,
            true
        )
        // 状态栏适配
        BarUtils.addMarginTopEqualStatusBarHeight(rootTitleLayout)
        // 全局标题事件点击监听
        _titleLayout?.let { binding ->
            binding.lifecycleOwner = this
            titleLayoutListener(this, binding)
        }
    }

    /**
     * 内容布局
     */
    private fun loadContentLayout() {
        rootContentLayout.removeAllViews()
        if (_contentLayout == null) {
            // 添加内容布局
            _contentLayout = DataBindingUtil.inflate(
                layoutInflater,
                contentLayoutId,
                rootContentLayout,
                true
            )
        } else {
            rootContentLayout.addView(_contentLayout?.root)
        }
        _contentLayout?.lifecycleOwner = this
    }

    /**
     * 加载布局
     *
     * @param msg 提示信息
     */
    private fun loadLoadingLayout(msg: String) {
        if (loadingLayout() == -1) {
            rootContentLayout.visibility = View.GONE
            return
        }
        rootContentLayout.removeAllViews()
        if (_loadingLayout == null) {
            // 添加加载布局
            _loadingLayout = DataBindingUtil.inflate(
                layoutInflater,
                loadingLayout(),
                rootContentLayout,
                true
            )
        } else {
            rootContentLayout.addView(_loadingLayout?.root)
        }
        _loadingLayout?.let { binding ->
            binding.lifecycleOwner = this
            loadingLayoutListener(this, binding, msg)
        }
    }

    /**
     * 错误布局
     *
     * @param msg 提示信息
     */
    private fun loadErrorLayout(msg: String) {
        if (errorLayout() == -1) {
            rootContentLayout.visibility = View.GONE
            return
        }
        rootContentLayout.removeAllViews()
        if (_errorLayout == null) {
            // 添加错误布局
            _errorLayout = DataBindingUtil.inflate(
                layoutInflater,
                errorLayout(),
                rootContentLayout,
                true
            )
        } else {
            rootContentLayout.addView(_errorLayout?.root)
        }
        _errorLayout?.let { binding ->
            binding.lifecycleOwner = this
            // 获取重新请求数据事件
            val btnRetry = binding.root.findViewById<View>(
                errorLayoutListener(
                    this,
                    binding,
                    msg
                )
            )
            // 重新加载
            ClickUtils.applySingleDebouncing(btnRetry) {
                onRetry()
            }
        }
    }

    /**
     * 空数据布局
     */
    private fun loadEmptyLayout(msg: String) {
        if (emptyLayout() == -1) {
            rootContentLayout.visibility = View.GONE
            return
        }
        rootContentLayout.removeAllViews()
        if (_emptyLayout == null) {
            // 添加空数据布局
            _emptyLayout = DataBindingUtil.inflate(
                layoutInflater,
                emptyLayout(),
                rootContentLayout,
                true
            )
        } else {
            rootContentLayout.addView(_emptyLayout?.root)
        }
        _emptyLayout?.let { binding ->
            binding.lifecycleOwner = this
            // 获取重新请求数据事件
            val btnRetry = binding.root.findViewById<View>(
                emptyLayoutListener(
                    this,
                    binding,
                    msg
                )
            )
            // 重新加载
            ClickUtils.applySingleDebouncing(btnRetry) {
                onRetry()
            }
        }
    }

    /**
     * 显示加载布局
     *
     * @param msg String 提示信息
     * @param isDialog Boolean 是否是Dialog, 如果为true则显示Dialog, 否则显示缺省页
     */
    fun showLoadingLayout(
        msg: String,
        isDialog: Boolean = false
    ) {
        if (isDialog) {
            if (_loadingDialog == null) {
                _loadingDialog = loadingDialog(this, msg)
            }
            showSuccessLayout()
            loadingDialogMsg(_loadingDialog!!, msg)
            if (_loadingDialog?.isShowing == false) {
                _loadingDialog?.show()
            }
        } else {
            loadLoadingLayout(msg)
        }
    }

    /**
     * 显示空数据布局
     *
     * @param msg String 提示信息
     * @param isToast Boolean 是否显示Toast
     */
    fun showEmptyLayout(
        msg: String,
        isToast: Boolean = false
    ) {
        _loadingDialog?.dismiss()
        if (isToast) {
            showSuccessLayout()
            msg.toast()
        } else {
            loadEmptyLayout(msg)
        }
    }

    /**
     * 显示错误布局
     *
     * @param msg String 提示信息
     * @param isToast Boolean 是否显示Toast
     */
    fun showErrorLayout(
        msg: String,
        isToast: Boolean = false
    ) {
        _loadingDialog?.dismiss()
        if (isToast) {
            showSuccessLayout()
            msg.toast()
        } else {
            loadErrorLayout(msg)
        }
    }

    /**
     * 加载成功
     */
    fun showSuccessLayout() {
        _loadingDialog?.dismiss()
        loadContentLayout()
    }

    /**
     * 标题布局DSL
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> titleLayout(title: T.() -> Unit) {
        title.invoke(
            _titleLayout as? T ?: throw NullPointerException("标题布局为空，请注意调用时机，需要在布局显示之后调用。")
        )
    }

    /**
     * 根据泛型获取根布局
     *
     * @param T 泛型
     * @return T
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> rootBinding(): T {
        return rootBinding as? T ?: throw NullPointerException("根布局为空")
    }

    /**
     * 根据泛型获取标题布局
     *
     * @param T 泛型
     * @return T
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> titleBinding(): T {
        return _titleLayout as? T ?: throw NullPointerException("标题布局为空")
    }

    /**
     * 获取内容布局
     *
     * @return T 指定类型的内容布局
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> contentBinding(): T {
        return _contentLayout as? T
            ?: throw NullPointerException("ContentLayout为空。")
    }

    /**
     * 获取加载布局
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> loadingBinding(): T {
        return _loadingLayout as? T
            ?: throw NullPointerException("LoadingLayout为空，请通过loadingLayout设置空布局。")
    }

    /**
     * 获取错误布局
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> errorBinding(): T {
        return _errorLayout as? T
            ?: throw NullPointerException("ErrorLayout为空，请通过errorLayout设置空布局。")
    }

    /**
     * 获取空数据布局
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> emptyBinding(): T {
        return _emptyLayout as? T
            ?: throw NullPointerException("EmptyLayout为空，请通过emptyLayout设置空布局。")
    }

    /**
     * 根据泛型获取Loading Dialog
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Dialog> loadingDialogBinding(): T {
        return _loadingDialog as? T
            ?: throw NullPointerException("LoadingDialog为空，请通过loadingDialog设置Dialog。")
    }

    override fun getResources(): Resources {
        if (screenWidth() == -1) {
            return super.getResources()
        }
        return if (super.getResources().configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            AdaptScreenUtils.adaptWidth(super.getResources(), screenWidth())
        } else {
            AdaptScreenUtils.adaptHeight(super.getResources(), screenWidth())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _loadingDialog?.dismiss()
    }

}