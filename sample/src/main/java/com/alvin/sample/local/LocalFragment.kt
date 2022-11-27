package com.alvin.sample.local

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.base.fragment.BaseVMFragment
import com.alvin.base_core.helper.DefaultUiEngine
import com.alvin.base_core.helper.IUiEngine
import com.alvin.base_core.utils.toast
import com.alvin.sample.R
import com.alvin.sample.custom.CustomLoadingDialog
import com.alvin.sample.databinding.*

/**
 * <h3> 作用类描述：默认的Fragment样式</h3>
 *
 * @Package :        com.alvin.sample.def
 * @Date :           2022/11/22
 * @author 高国峰
 */
class LocalFragment : BaseVMFragment<FragmentLocalBinding, LocalVM>(
    R.layout.fragment_default
) {
    /**
     * 标题布局
     */
    private val titleBinding by lazy {
        titleBinding<CustomTitleLayoutBinding>()
    }

    /**
     * 加载布局
     */
    private val loadingLayoutBinding by lazy {
        loadingBinding<CustomLoadingLayoutBinding>()
    }

    /**
     * 加载Dialog
     */
    private val loadingDialog by lazy {
        loadingDialogBinding<CustomLoadingDialog>()
    }

    /**
     * 空布局
     */
    private val emptyLayoutBinding by lazy {
        emptyBinding<CustomEmptyLayoutBinding>()
    }

    /**
     * 错误布局
     */
    private val errorLayoutBinding by lazy {
        errorBinding<CustomErrorLayoutBinding>()
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        // 设置标题
        /*titleBinding.ivBack.setOnClickListener {
            finish()
        }
        titleBinding.tvTitle.text = "局部设置"
        titleBinding.ivRightImage.setOnClickListener {
            "点击了右侧菜单".toast()
        }*/
/*        titleLayout<CustomTitleLayoutBinding> {
            ivBack.setOnClickListener {
                finish()
            }
            tvTitle.text = "局部设置"
            ivRightImage.setOnClickListener{
                "点击了右侧菜单".toast()
            }
        }*/
    }

    override fun obtainData() {
        // 模拟网络请求
        vm.sendHttpTestError()
    }

    override fun registerObserver() {
        // 监听数据回传
        vm.testData.observe(this) {
            binding.data = it
        }
    }

    override fun setUiEngine(): IUiEngine {
        return DefaultUiEngine()
    }

    override fun rootLayout(): Int {
        return R.layout.custom_root_layout
    }

    override fun rootLayoutListener(activity: Activity, rootBinding: ViewDataBinding) {
        // 判断是否是自定义的根布局
        if (rootBinding is CustomRootLayoutBinding) {
            rootBinding.tvTip.text = activity.javaClass.simpleName
        }
    }

    override fun titleLayout(): Int {
        return R.layout.custom_title_layout
    }

    override fun titleLayoutIsShow(): Boolean {
        // 不显示标题
//        return false
        // 显示标题
        return true
    }

    override fun titleLayoutListener(activity: Activity, titleBinding: ViewDataBinding) {
        if (titleBinding is CustomTitleLayoutBinding) {
            titleBinding.ivBack.setOnClickListener {
                activity.finish()
            }
            titleBinding.tvTitle.text = "局部设置"
            titleBinding.ivRightImage.setOnClickListener {
                "点击了右侧菜单".toast()
            }
        }
    }

    override fun loadingLayout(): Int {
        return R.layout.custom_loading_layout
    }

    override fun loadingLayoutListener(
        activity: Activity,
        loadingBinding: ViewDataBinding,
        msg: String
    ) {
        // 判断是否是自定义的加载布局
        if (loadingBinding is CustomLoadingLayoutBinding) {
            // 设置加载提示颜色
            loadingLayoutBinding.tvMsg.setTextColor(
                ContextCompat.getColor(
                    activity,
                    R.color.purple_500
                )
            )
            // 设置当前类名
            loadingBinding.tvClassName.text = activity.javaClass.simpleName
            // 设置加载提示
            loadingBinding.tvMsg.text = msg
        }
    }

    override fun loadingDialog(activity: Activity, msg: String): Dialog {
        return CustomLoadingDialog(activity, msg)
    }

    override fun loadingDialogMsg(dialog: Dialog, msg: String) {
        if (dialog is CustomLoadingDialog) {
            loadingDialog.changeColor(ContextCompat.getColor(requireActivity(), R.color.purple_500))
            dialog.changeMsg(msg)
        }
    }

    override fun emptyLayout(): Int {
        return R.layout.custom_empty_layout
    }

    override fun emptyLayoutListener(
        activity: Activity,
        emptyBinding: ViewDataBinding,
        msg: String
    ): Int {
//        if (emptyBinding is CustomEmptyLayoutBinding){
//            // 设置当前类名
//            emptyBinding.tvClassName.text = activity.javaClass.simpleName
//            // 设置空数据提示
//            emptyBinding.tvMsg.text = msg
//        }
        emptyLayoutBinding.tvClassName.text = activity.javaClass.simpleName
        emptyLayoutBinding.tvMsg.text = msg
        // 返回重试View的ID
        return R.id.btn_retry
    }

    override fun errorLayout(): Int {
        return R.layout.custom_error_layout
    }

    override fun errorLayoutListener(
        activity: Activity,
        errorBinding: ViewDataBinding,
        msg: String
    ): Int {
//        if (errorBinding is CustomErrorLayoutBinding){
//            // 设置当前类名
//            errorBinding.tvClassName.text = activity.javaClass.simpleName
//            // 设置错误提示
//            errorBinding.tvMsg.text = msg
//        }
        errorLayoutBinding.tvClassName.text = activity.javaClass.simpleName
        errorLayoutBinding.tvMsg.text = msg
        // 返回重试View的ID
        return R.id.btn_retry
    }

    override fun onRetry() {
        "重新尝试".toast()
    }
}