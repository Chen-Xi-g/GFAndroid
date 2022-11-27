package com.alvin.sample.def

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.base_core.helper.DefaultUiEngine
import com.alvin.base_core.helper.IUiEngine
import com.alvin.sample.R
import com.alvin.sample.databinding.ActivityDefaultBinding

/**
 * <h3> 作用类描述：默认的Activity页面</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class DefaultActivity : BaseVMActivity<ActivityDefaultBinding, DefaultVM>(
    R.layout.activity_default
) {

    private val default2VM by lazy {
        ViewModelProvider(this)[Default2VM::class.java]
    }

    override fun initView(savedInstanceState: Bundle?) {
        addLoadingObserve(default2VM)
        binding.tvTitle.text = "默认的Activity页面"
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
     * 局部UI配置，使用默认的UI配置，否则会被Application中的配置覆盖
     */
    override fun setUiEngine(): IUiEngine {
        return DefaultUiEngine()
    }
}