package com.alvin.base_core.base.activity

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alvin.base_core.base.vm.BaseVM
import com.alvin.base_core.base.vm.UIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

/**
 * <h3> 作用类描述：Activity与ViewModel相关操作</h3>
 *
 * @Package :        com.alvin.base_core.base.activity
 * @Date :           2022/11/18
 * @author 高国峰
 */
abstract class BaseVMActivity<DB : ViewDataBinding, VM : BaseVM>(
    @LayoutRes contentLayoutId: Int
) : BaseContentActivity<DB>(contentLayoutId) {

    /**
     * 获取泛型中的ViewModel实例
     */
    val vm by lazy { initVM() }

    /**
     * 获取内容布局的ViewDataBinding
     */
    val binding: DB by lazy {
        contentBinding()
    }

    override fun initBinding() {
        super.initBinding()
        rootBinding.lifecycleOwner = this
        registerHttpCallback()
        registerObserver()
    }

    /**
     * 注册VM的监听
     */
    private fun registerHttpCallback() {
        lifecycleScope.launch {
            vm.uiState.receiveAsFlow().collect { uiState ->
                when (uiState) {
                    is UIState.Loading -> {
                        // 请求开始
                        showLoadingLayout(uiState.msg, uiState.isDialog)
                    }
                    is UIState.Empty -> {
                        // 请求结束，数据为空
                        showEmptyLayout(uiState.msg, uiState.isToast)
                    }
                    is UIState.Success -> {
                        // 请求结束，数据不为空
                        showSuccessLayout()
                    }
                    is UIState.Error -> {
                        // 请求发生错误
                        showErrorLayout(uiState.msg, uiState.isToast)
                    }
                }
            }
        }
    }

    /**
     * ViewModel 通过非泛型绑定时，注册监听
     */
    fun addLoadingObserve(vararg viewModels: BaseVM) {
        lifecycleScope.launch {
            viewModels.forEach { vm ->
                vm.uiState.receiveAsFlow().collect { uiState ->
                    when (uiState) {
                        is UIState.Loading -> {
                            // 请求开始
                            showLoadingLayout(uiState.msg, uiState.isDialog)
                        }
                        is UIState.Empty -> {
                            // 请求结束，数据为空
                            showEmptyLayout(uiState.msg, uiState.isToast)
                        }
                        is UIState.Success -> {
                            // 请求结束，数据不为空
                            showSuccessLayout()
                        }
                        is UIState.Error -> {
                            // 请求发生错误
                            showErrorLayout(uiState.msg, uiState.isToast)
                        }
                    }
                }
            }
        }
    }


    /**
     * 创建viewModel
     */
    private fun initVM(): VM {
        return ViewModelProvider(this)[getVmClazz(this)]
    }

    /**
     * 获取当前类绑定的泛型ViewModel-clazz
     */
    @Suppress("UNCHECKED_CAST")
    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
    }
}