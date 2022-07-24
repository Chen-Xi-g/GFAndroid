package com.alvin.base_core.base

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.alvin.base_core.base.view_model.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * <h3> 作用类描述：实现ViewModel的Activity基类</h3>
 *
 * @Package :        com.alvin.base_core.base
 * @Date :           2022/7/24
 * @author 高国峰
 */
abstract class BaseVMActivity<VM : BaseViewModel>(@LayoutRes private val contentLayoutRes: Int) :
    BaseNetworkActivity(contentLayoutRes) {

    /**
     * 获取泛型中的ViewModel实例
     */
    val viewModel by lazy {
        createViewModel()
    }

    override fun initBinding() {
        super.initBinding()
        registerHttpCallback()
        registerObserver()
    }

    /**
     * 注册HttpCallback
     */
    private fun registerHttpCallback() {
        // 请求结束
        viewModel.httpCallback.afterNetwork.observe(this) {
            afterNetwork(it)
        }
        // 请求开始
        viewModel.httpCallback.beforeNetwork.observe(this) {
            beforeNetwork(it)
        }
        // 请求发生错误
        viewModel.httpCallback.failed.observe(this) {
            failed(it)
        }
    }

    /**
     * 获取当前类绑定的泛型ViewModel-clazz
     */
    @Suppress("UNCHECKED_CAST")
    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVmClazz(this)]
    }

    /**
     * ViewModel 通过非泛型绑定时，注册Loading监听
     */
    fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            // 请求结束
            viewModel.httpCallback.afterNetwork.observe(this) {
                afterNetwork(it)
            }
            // 请求开始
            viewModel.httpCallback.beforeNetwork.observe(this) {
                beforeNetwork(it)
            }
            // 请求发生错误
            viewModel.httpCallback.failed.observe(this) {
                failed(it)
            }
        }
    }
}