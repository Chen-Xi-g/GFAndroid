package com.alvin.sample.sample

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.alvin.base_core.base.activity.BaseVMActivity
import com.alvin.sample.R
import com.alvin.sample.custom.CustomLoadingDialog
import com.alvin.sample.databinding.ActivitySampleBinding
import com.alvin.sample.databinding.CustomTitleLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <h3> 作用类描述：演示Activity</h3>
 *
 * @Package :        com.alvin.sample.sample
 * @Date :           2022/11/26
 * @author 高国峰
 */
class SampleActivity : BaseVMActivity<ActivitySampleBinding, SampleVM>(
    R.layout.activity_sample
) {
    /**
     * 菜单
     */
    private val menuList = arrayOf(
        "加载布局",
        "空布局",
        "错误布局",
        "成功布局"
    )

    /**
     * 菜单展示形式
     */
    private val menuStatusList = arrayOf(
        "缺省页显示",
        "Dialog + Toast显示"
    )

    private val dialog by lazy {
        loadingDialogBinding<CustomLoadingDialog>()
    }

    override fun initView(savedInstanceState: Bundle?) {
        titleLayout<CustomTitleLayoutBinding> {
            tvTitle.text = "演示Activity"
            ivRightImage.setOnClickListener {
                // 右侧菜单点击
                menuClick()
            }
        }
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
                                showLoadingLayout("加载中...", childWhich == 1)
                                if (childWhich == 1) {
                                    lifecycleScope.launch {
                                        delay(5000)
                                        this@SampleActivity.dialog.dismiss()
                                    }
                                }
                            }
                            1 -> {
                                // 空布局
                                showEmptyLayout("暂无数据", childWhich == 1)
                            }
                            2 -> {
                                // 错误布局
                                showErrorLayout("加载失败", childWhich == 1)
                            }
                            3 -> {
                                // 成功布局
                                showSuccessLayout()
                            }
                        }
                    }.show()
            }.show()
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }
}