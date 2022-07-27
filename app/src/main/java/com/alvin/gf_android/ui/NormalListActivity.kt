package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.alvin.gf_android.R
import com.alvin.gf_android.base.BaseGFActivity
import com.alvin.gf_android.databinding.ActivityNormalListBinding
import com.alvin.gf_android.databinding.ItemNormalBinding
import com.alvin.gf_android.model.NormalEntity
import com.alvin.gf_android.vm.NormalViewModel
import com.alvin.gfad.utils.*
import com.bumptech.glide.Glide
import java.util.*

/**
 * <h3> 作用类描述：普通的列表Activity</h3>
 *
 * @Package :        com.alvin.gf_android.ui
 * @Date :           2022/7/27
 * @author 高国峰
 */
class NormalListActivity :
    BaseGFActivity<NormalViewModel, ActivityNormalListBinding>(
        R.layout.activity_normal_list,
        true
    ) {

    companion object {
        fun newIntent(context: Context) = Intent(context, NormalListActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitleName("普通列表")
        setMenuText("列表事件") {
            AlertDialog.Builder(this)
                .setTitle("列表事件")
                .setItems(arrayOf("删除Item", "添加Item", "更新Item")) { dialog, which ->
                    when (which) {
                        0 -> {
                            binding.list.removeAt(1)
                        }
                        1 -> {
                            // 创建Int随机数
                            val random = Random()
                            val randomInt = random.nextInt(100)
                            val entity = NormalEntity(
                                randomInt,
                                "我是新添加的Item${randomInt}",
                                "我是新添加新的Item内容${randomInt}",
                                "http://cdn.u2.huluxia.com/g3/M02/36/6C/wKgBOVwPoAmASl2jAADug4ZLXoo747.jpg"
                            )
                            binding.list.addData(listOf(entity), 1)
                        }
                        2 -> {
                            val item = binding.list.data[1]
                            if (item is NormalEntity) {
                                item.title = "我是更新的Item"
                                item.content = "我是更新的Item内容"
                                item.imageUrl =
                                    "http://cdn.u2.huluxia.com/g3/M00/36/56/wKgBOVwPmdqAe61PAABw0fzQb_4559.jpg"
                            }
                            binding.list.setData(1, item!!)
                        }
                    }
                }.create().show()
        }
        initAdapter()
    }

    override fun obtainData() {
        viewModel.getTestData()
    }

    override fun registerObserver() {
        viewModel.listData.observe(this) {
            binding.list.setData(it)
        }
    }

    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        binding.list.linear().setup {
            addType<NormalEntity>(R.layout.item_normal)
            onBind {
                val itemBinding = getBinding<ItemNormalBinding>()
                itemBinding?.item = getItem()
                itemBinding?.itemIvCover?.let {
                    Glide.with(it.context)
                        .asBitmap()
                        .load(getItem<NormalEntity>().imageUrl)
                        .into(it)
                }
                itemBinding?.executePendingBindings()
            }
        }
    }
}