package com.alvin.sample.custom

import android.os.Bundle
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.base_core.utils.toast
import com.alvin.sample.R
import com.alvin.sample.databinding.ActivityCustomBinding

/**
 * <h3> 作用类描述：自定义全局UI配置的Activity</h3>
 *
 * @Package :        com.alvin.sample.custom
 * @Date :           2022/11/25
 * @author 高国峰
 */
class CustomActivity : BaseVMActivity<ActivityCustomBinding, CustomVM>(
    R.layout.activity_custom
) {
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun obtainData() {
        // 模拟网络请求
        vm.sendHttpTestError()
    }

    override fun registerObserver() {
        // 监听数据回传
        vm.testData.observe(this) {
            binding.data = it
        }
    }

    override fun onRetry() {
        "Activity#onRetry".toast()
    }
}