package com.alvin.sample.def

import android.os.Bundle
import android.view.View
import com.alvin.base_core.base.fragment.BaseVMFragment
import com.alvin.base_core.helper.DefaultUiEngine
import com.alvin.base_core.helper.IUiEngine
import com.alvin.sample.R
import com.alvin.sample.databinding.FragmentDefaultBinding

/**
 * <h3> 作用类描述：默认的Fragment样式</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class DefaultFragment : BaseVMFragment<FragmentDefaultBinding, DefaultVM>(
    R.layout.fragment_default
) {
    override fun initView(view: View, savedInstanceState: Bundle?) {
    }

    override fun obtainData() {
        // 模拟网络请求
        vm.sendHttpTestData()
    }

    override fun registerObserver() {
        // 监听数据回传
        vm.testData.observe(this) {
            binding.data = it
        }
    }

    /**
     * 取消显示标题栏
     */
    override fun titleLayoutIsShow(): Boolean {
        return false
    }

    /**
     * 局部UI配置，使用默认的UI配置，否则会被Application中的配置覆盖
     */
    override fun setUiEngine(): IUiEngine {
        return DefaultUiEngine()
    }
}