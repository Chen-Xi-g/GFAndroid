package com.alvin.sample.sample

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alvin.base_core.base.fragment.BaseVMFragment
import com.alvin.sample.R
import com.alvin.sample.custom.CustomLoadingDialog
import com.alvin.sample.databinding.CustomTitleLayoutBinding
import com.alvin.sample.databinding.FragmentSampleBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <h3> 作用类描述：演示Fragment</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class SampleFragment : BaseVMFragment<FragmentSampleBinding, SampleVM>(
    R.layout.fragment_sample
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

    override fun initView(view: View, savedInstanceState: Bundle?) {
        titleLayout<CustomTitleLayoutBinding> {
            tvTitle.text = "演示Activity"
            ivRightImage.setOnClickListener {
                // 右侧菜单点击
                menuClick()
            }
        }
    }

    private fun menuClick() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("缺省页")
            .setItems(
                menuList
            ) { dialog, which ->
                dialog?.dismiss()
                MaterialAlertDialogBuilder(requireActivity())
                    .setTitle("缺省页展示形式")
                    .setItems(menuStatusList) { childDialog, childWhich ->
                        when (which) {
                            0 -> {
                                // 加载布局
                                showLoadingLayout("加载中...", childWhich == 1)
                                if (childWhich == 1) {
                                    lifecycleScope.launch {
                                        delay(5000)
                                        this@SampleFragment.dialog.dismiss()
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