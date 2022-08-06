package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.alvin.gf_android.R
import com.alvin.gf_android.base.BaseGFActivity
import com.alvin.gf_android.databinding.ActivityExpandBinding
import com.alvin.gf_android.databinding.ItemExpandBinding
import com.alvin.gf_android.model.ExpandEntity
import com.alvin.gf_android.vm.ExpandViewModel
import com.alvin.gfad.layout_manager.StickyGridLayoutManager
import com.alvin.gfad.utils.reuseAdapter
import com.alvin.gfad.utils.setData
import com.alvin.gfad.utils.setup

class ExpandActivity :
    BaseGFActivity<ExpandViewModel, ActivityExpandBinding>(R.layout.activity_expand) {

    companion object {
        fun newIntent(context: Context) = Intent(context, ExpandActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitleName("展开收缩")
        binding.expandList.layoutManager = StickyGridLayoutManager(this, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (binding.expandList.reuseAdapter.isSticky(position)) 2 else 1
                }
            }
        }
        binding.expandList.setup {
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
        vm.onExpand()
    }

    override fun registerObserver() {
        vm.listData.observe(this) {
            binding.expandList.setData(it)
        }
    }
}