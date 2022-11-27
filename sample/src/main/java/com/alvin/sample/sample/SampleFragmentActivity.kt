package com.alvin.sample.sample

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.sample.R
import com.alvin.sample.databinding.ActivitySampleFragmentBinding
import com.alvin.sample.databinding.CustomRootLayoutBinding
import com.alvin.sample.def.DefaultVM

/**
 * <h3> 作用类描述：装载Fragment的容器</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class SampleFragmentActivity : BaseVMActivity<ActivitySampleFragmentBinding, DefaultVM>(
    R.layout.activity_sample_fragment
) {
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }

    override fun rootLayoutListener(activity: Activity, rootBinding: ViewDataBinding) {
        if (rootBinding is CustomRootLayoutBinding) {
            rootBinding.tvTip.visibility = View.GONE
        }
    }

    override fun titleLayoutIsShow(): Boolean {
        return false
    }
}