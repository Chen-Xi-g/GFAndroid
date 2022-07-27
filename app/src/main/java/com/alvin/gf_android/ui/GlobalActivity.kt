package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alvin.gf_android.R
import com.alvin.gf_android.base.BaseGFActivity
import com.alvin.gf_android.databinding.ActivityGlobalBinding
import com.alvin.gf_android.utils.toast
import com.alvin.gf_android.vm.GlobalViewModel

/**
 * <h3> 作用类描述：全局Activity设置</h3>
 *
 * @Package :        com.alvin.gf_android.ui
 * @Date :           2022/7/27
 * @author 高国峰
 */
class GlobalActivity :
    BaseGFActivity<GlobalViewModel, ActivityGlobalBinding>(R.layout.activity_global) {

    companion object {
        fun newIntent(context: Context) = Intent(context, GlobalActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitleName("全局Activity")
        setMenuIcon(R.drawable.ic_action_menu) {
            "Menu".toast()
        }
        showErrorLayout()
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }

    override fun onErrorLayoutRetryClick() {
        super.onErrorLayoutRetryClick()
    }
}