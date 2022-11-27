package com.alvin.sample.custom

import android.os.Bundle
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.sample.R
import com.alvin.sample.databinding.ActivityCustomFragmentBinding
import com.alvin.sample.def.DefaultVM

/**
 * <h3> 作用类描述：装载Fragment的容器</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class CustomFragmentActivity : BaseVMActivity<ActivityCustomFragmentBinding, DefaultVM>(
    R.layout.activity_custom_fragment
) {
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }

    override fun titleLayoutIsShow(): Boolean {
        return false
    }
}