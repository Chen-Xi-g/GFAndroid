package com.alvin.sample.def

import android.os.Bundle
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.sample.R
import com.alvin.sample.databinding.ActivityDefaultBinding

/**
 * <h3> 作用类描述：装载Fragment的容器</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class DefaultFragmentActivity : BaseVMActivity<ActivityDefaultBinding, DefaultVM>(
    R.layout.activity_default_fragment
) {
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }
}