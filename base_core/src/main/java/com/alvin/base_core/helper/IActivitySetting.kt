package com.alvin.base_core.helper

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.R
import com.alvin.base_core.model.BarModel

/**
 * <h3> 作用类描述：设置Activity默认的相关属性</h3>
 *
 * <p> 提供默认的全局Activity属性设置, 避免每次创建新的项目都需要进行底层修改. <p>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/7/23
 * @author 高国峰
 */
interface IActivitySetting {

    /**
     * 初始化Bar的相关属性
     */
    fun barModel(): BarModel

    /**
     * 获取全局Dialog属性
     */
    fun dialogSetting(): IDialogSetting = DefaultSettingDialog()


    /*        标题栏属性              */

    /**
     * 设置标题栏布局
     */
    @LayoutRes
    fun titleBarLayout(): Int = R.layout.layout_gf_android_mvvm_base_title

    /**
     * 是否需要显示标题栏布局
     */
    fun isShowTitleLayout(): Boolean

    /**
     * 自定义成功布局内部逻辑
     *
     * @param activity
     * @param titleLayoutBinding ViewDataBinding
     */
    fun titleLayoutClickListener(activity: Activity, titleLayoutBinding: ViewDataBinding)

    /*        正在加载属性              */

    /**
     * 设置加载布局
     */
    @LayoutRes
    fun loadingLayout(): Int = R.layout.layout_gf_android_mvvm_base_loading

    /*        错误属性              */

    /**
     * 设置错误布局
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
     */
    @LayoutRes
    fun emptyLayout(): Int = R.layout.layout_gf_android_mvvm_base_empty
}