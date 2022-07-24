package com.alvin.base_core.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.base.view_model.BaseViewModel

/**
 * <h3> 作用类描述：所有Activity最终需要继承的MVVM类</h3>
 *
 * @Package :        com.alvin.base_core.base
 * @Date :           2022/7/24
 * @author 高国峰
 *
 * @param contentLayoutRes 内容布局
 * @param defaultLoadingLayout 是否需要默认显示加载中布局, 当调用[com.alvin.base_core.base.view_model.BaseViewModel.HttpCallback.afterNetwork]后显示内容布局.
 */
abstract class BaseMVVMActivity<VM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes private val contentLayoutRes: Int,
    private val defaultLoadingLayout: Boolean = false
) : BaseVMActivity<VM>(contentLayoutRes) {

    /**
     * 获取内容布局的ViewDataBinding
     */
    val binding by lazy {
        getContentLayout<DB>()
    }


    override fun initBinding() {
        super.initBinding()
        if (defaultLoadingLayout) {
            showLoadingLayout()
        } else {
            showContentLayout()
        }
        binding.lifecycleOwner = this
    }

}