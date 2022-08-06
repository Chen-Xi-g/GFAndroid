package com.alvin.base_core.helper

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alvin.base_core.R
import com.alvin.base_core.model.BarModel

/**
 * <h3> 作用类描述：设置Fragment默认的相关属性</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/8/6
 * @author 高国峰
 */
interface IFragmentSetting {

    /**
     * 初始化Bar的相关属性
     *
     * 如果在Fragment中设置，会覆盖原有Activity的设置
     */
    fun barModel(): BarModel

    /**
     * 获取全局Dialog属性
     */
    fun dialogSetting(): IDialogSetting = DefaultSettingDialog()


    /*        标题栏属性              */

    /**
     * 设置标题栏布局
     *
     * 当返回值为-1时, 则不显示标题栏
     */
    @LayoutRes
    fun titleBarLayout(): Int = R.layout.layout_gf_android_mvvm_base_title

    /**
     * 是否需要显示标题栏布局
     */
    fun isShowTitleLayout(): Boolean

    /**
     * 标题布局回调
     *
     * @param fragment
     * @param titleLayoutBinding ViewDataBinding
     */
    fun titleLayoutClickListener(fragment: Fragment, titleLayoutBinding: ViewDataBinding)

    /*        正在加载属性              */

    /**
     * 设置加载布局
     *
     * 当返回值为-1时, 则不显示加载布局
     */
    @LayoutRes
    fun loadingLayout(): Int = R.layout.layout_gf_android_mvvm_base_loading

    /*        错误属性              */

    /**
     * 设置错误布局
     *
     * 当返回值为-1时, 则不显示错误布局
     */
    @LayoutRes
    fun errorLayout(): Int = R.layout.layout_gf_android_mvvm_base_error

    /**
     * 点击重试的ViewID,点击时进行重试操作.
     */
    fun errorRetryId(): Int = R.id.tvError

    /**
     * 显示错误信息的ViewID
     */
    fun errorMessageId(): Int = R.id.tvError

    /**
     * 当发生错误时, 显示Toast还是显示错误布局
     *
     * @return Boolean true: 显示Toast, false: 显示错误布局
     */
    fun isErrorToast(): Boolean = true

    /*        空数据属性              */

    /**
     * 设置空数据布局
     *
     * 当返回值为-1时, 则不显示空数据布局
     */
    @LayoutRes
    fun emptyLayout(): Int = R.layout.layout_gf_android_mvvm_base_empty
}