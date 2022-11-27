package com.alvin.sample.vm

import android.os.Bundle
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.base_core.utils.clickNotRepeat
import com.alvin.sample.R
import com.alvin.sample.databinding.ActivitySampleVmBinding
import com.alvin.sample.databinding.CustomTitleLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * <h3> 作用类描述：演示VM更新缺省页</h3>
 *
 * @Package :        com.alvin.sample.vm
 * @Date :           2022/11/26
 * @author 高国峰
 */
class SampleVMActivity : BaseVMActivity<ActivitySampleVmBinding, SampleVM>(
    R.layout.activity_sample_vm
) {

    /**
     * 菜单
     */
    private val menuList = arrayOf(
        "请求成功",
        "请求失败",
        "请求为空"
    )

    /**
     * 菜单展示形式
     */
    private val menuStatusList = arrayOf(
        "缺省页显示",
        "Dialog + Toast显示"
    )

    override fun initView(savedInstanceState: Bundle?) {
        titleLayout<CustomTitleLayoutBinding> {
            tvTitle.text = "演示VM更新缺省页"
            ivRightImage.setOnClickListener {
                // 右侧菜单点击
                menuClick()
            }
            ivRightImage.clickNotRepeat {

            }
        }
    }

    override fun obtainData() {
    }

    private fun menuClick() {
        MaterialAlertDialogBuilder(this)
            .setTitle("缺省页")
            .setItems(
                menuList
            ) { dialog, which ->
                dialog?.dismiss()
                MaterialAlertDialogBuilder(this)
                    .setTitle("缺省页展示形式")
                    .setItems(menuStatusList) { childDialog, childWhich ->
                        when (which) {
                            0 -> {
                                // 加载布局
                                vm.sendHttpSuccess(childWhich == 0)
                            }
                            1 -> {
                                // 失败布局
                                vm.sendHttpError(childWhich == 0)
                            }
                            2 -> {
                                // 空布局
                                vm.sendHttpEmpty(childWhich == 0)
                            }
                        }
                    }.show()
            }.show()
    }

    override fun registerObserver() {
        // 监听数据回传
        vm.testData.observe(this) {
            binding.data = it
        }
    }
}