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
import com.alvin.gf_android.utils.toast
import com.alvin.gf_android.vm.NormalViewModel
import com.alvin.gfad.mode.SelectSealed
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
                .setItems(
                    arrayOf(
                        "删除Item",
                        "添加Item",
                        "更新Item",
                        "单选",
                        "多选",
                        "全选",
                        "取消全选"
                    )
                ) { dialog, which ->
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
                            binding.list.addData(entity, 1)
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
                        3 -> {
                            binding.list.reuseAdapter.selectModel = SelectSealed.Single
                            binding.list.reuseAdapter.checkedAll(false)
                        }
                        4 -> {
                            binding.list.reuseAdapter.selectModel = SelectSealed.Multiple
                            binding.list.reuseAdapter.checkedAll(false)
                        }
                        5 -> {
                            binding.list.reuseAdapter.checkedAll()
                        }
                        6 -> {
                            binding.list.reuseAdapter.checkedAll(false)
                        }
                    }
                }.create().show()
        }
        initAdapter()
    }

    override fun obtainData() {
        vm.getTestData()
    }

    override fun registerObserver() {
        vm.listData.observe(this) {
            binding.list.setData(it)
        }
    }

    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        binding.list.linear().setup {
            selectModel = SelectSealed.Single
            addType<NormalEntity>(R.layout.item_normal)
            // onBindViewHolder 回调
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
            // Item点击事件
            onItemClick {
                // 删除点击的Item
                removeAt(adapterPosition)
            }
            // Item长按事件
            onItemLongClick {
                "长按了第${adapterPosition}个Item".toast()
            }
            // 子Item点击事件
            addOnItemChildClickListener(R.id.itemIvCover, R.id.itemTvContent) { viewId ->
                when (viewId) {
                    R.id.itemIvCover -> {
                        "点击了第${adapterPosition}个Item的图片".toast()
                    }
                    R.id.itemTvContent -> {
                        // 选择该条目
                        checkedSwitch(adapterPosition)
                    }
                }
            }
            // 子Item长按事件
            addOnItemChildLongClickListener(R.id.itemIvCover, R.id.itemTvContent) { viewId ->
                when (viewId) {
                    R.id.itemIvCover -> {
                        checkedSwitch(adapterPosition)
                    }
                    R.id.itemTvContent -> {
                        "长按了第${adapterPosition}个Item的文字".toast()
                    }
                }
            }
        }
    }
}