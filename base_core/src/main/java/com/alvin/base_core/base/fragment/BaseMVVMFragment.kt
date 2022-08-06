package com.alvin.base_core.base.fragment

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.base.view_model.BaseViewModel

/**
 * <h3> 作用类描述：所有Fragment最终需要继承的MVVM类</h3>
 *
 * @Package :        com.alvin.base_core.base.fragment
 * @Date :           2022/8/6
 * @author 高国峰
 *
 * @property VM BaseViewModel
 * @property DB ViewDataBinding
 *
 * @param contentLayoutRes 内容布局资源id
 * @param defaultLoadingLayout 是否默认显示加载布局
 */
abstract class BaseMVVMFragment<VM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes private val contentLayoutRes: Int,
    private val defaultLoadingLayout: Boolean = false
) : BaseVMFragment<VM>(contentLayoutRes) {

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