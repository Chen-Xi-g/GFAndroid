package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alvin.gf_android.R
import com.alvin.gf_android.base.BaseGFActivity
import com.alvin.gf_android.databinding.ActivityExpandBinding
import com.alvin.gf_android.databinding.ItemExpandBinding
import com.alvin.gf_android.model.ExpandEntity
import com.alvin.gf_android.vm.ExpandViewModel
import com.alvin.gfad.utils.linear
import com.alvin.gfad.utils.setData
import com.alvin.gfad.utils.setup

class ExpandActivity :
    BaseGFActivity<ExpandViewModel, ActivityExpandBinding>(R.layout.activity_expand) {

    companion object {
        fun newIntent(context: Context) = Intent(context, ExpandActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitleName("展开收缩")
        binding.expandList.linear().setup {
            addType<ExpandEntity>(R.layout.item_expand)
            onBind {
                getBinding<ItemExpandBinding>()?.item = getItem()
            }
            onItemClick {
                expandOrCollapse(true)
            }
        }
    }

    override fun obtainData() {
        viewModel.onExpand()
    }

    override fun registerObserver() {
        viewModel.listData.observe(this) {
            binding.expandList.setData(it)
        }
    }
}