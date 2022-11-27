package com.alvin.base_core.helper

import android.app.Activity
import android.app.Dialog
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * <h3> 作用类描述：Activity Fragment 通用函数</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/11/17
 * @author 高国峰
 */
interface IUiEngine {

    /**
     * 需要适配的屏幕宽度
     *
     * 具体适配规则参考：[Android 屏幕适配终结者](https://github.com/Blankj/AndroidUtilCode/issues/758)
     *
     * @return 屏幕宽度，值为-1时不适配
     */
    val screenWidth: Int

    /**
     * 自定义状态栏、导航栏
     *
     * @param activity Activity 只有在Activity中才会回调，Fragment中不会回调
     */
    fun setStatusBar(activity: Activity)

    /**
     * 自定义根布局
     *
     * 如果根据自己的需求定义根布局，根布局中必须有一个id为`root_title`的FrameLayout，用于放置标题栏。
     * 还必须有一个id为`root_content`的FrameLayout，用于放置缺省页及内容。
     *
     * @return Int 布局ID
     */
    @LayoutRes
    fun rootLayout(): Int

    /**
     * 根布局通用事件处理
     * 默认不会进行任何操作，提供给开发者自定义根布局时，进行通用事件处理。
     *
     * @param activity 返回当前Activity，如果是Fragment则返回当前Fragment所在的Activity
     * @param rootBinding 标题栏绑定的ViewDataBinding，使用`if(titleBinding is XXXBinding){}`进行判断
     */
    fun rootLayoutListener(activity: Activity, rootBinding: ViewDataBinding)


    /**
     * 标题栏布局
     *
     * 当返回值为-1时, 则不显示标题栏
     *
     * @return Int 标题栏布局
     */
    @LayoutRes
    fun titleLayout(): Int

    /**
     * 是否显示标题栏
     *
     * @return Boolean true: 显示标题栏 false: 不显示标题栏
     */
    fun titleLayoutIsShow(): Boolean

    /**
     * 标题栏通用事件处理
     * 默认在点击Back时关闭当前Activity。
     *
     * @param activity 返回当前Activity，如果是Fragment则返回当前Fragment所在的Activity
     * @param titleBinding 标题栏绑定的ViewDataBinding，使用`if(titleBinding is XXXBinding){}`进行判断
     */
    fun titleLayoutListener(activity: Activity, titleBinding: ViewDataBinding)

    /**
     * 加载中布局
     *
     * @return Int 加载中布局
     */
    @LayoutRes
    fun loadingLayout(): Int

    /**
     * 加载通用事件处理
     * 默认不会进行任何操作，提供给开发者自定义加载中布局时，进行通用事件处理。
     *
     * @param activity 返回当前Activity，如果是Fragment则返回当前Fragment所在的Activity
     * @param loadingBinding 加载中绑定的ViewDataBinding，使用`if(loadingBinding is XXXBinding){}`进行判断
     * @param msg 当加载空布局数据时，根据loadingBinding进行提示。
     */
    fun loadingLayoutListener(activity: Activity, loadingBinding: ViewDataBinding, msg: String)

    /**
     * Loading Dialog 创建
     *
     * 用户提交数据时，显示Loading Dialog
     *
     * @param activity Activity 当前Activity
     * @param msg String 显示的信息
     * @return Dialog 返回创建的Dialog
     */
    fun loadingDialog(
        activity: Activity,
        msg: String
    ): Dialog

    /**
     * 更新Loading Dialog内容
     *
     * @param dialog Dialog 当前Dialog
     * @param msg String 显示的信息，根据缺省页的状态显示不同的信息
     */
    fun loadingDialogMsg(dialog: Dialog, msg: String)

    /**
     * 空数据布局
     *
     * @return Int 空数据布局
     */
    @LayoutRes
    fun emptyLayout(): Int

    /**
     * 空数据通用事件处理
     * 默认不会进行任何操作，提供给开发者自定义空数据布局时，进行通用事件处理。
     *
     * @param activity 返回当前Activity，如果是Fragment则返回当前Fragment所在的Activity
     * @param emptyBinding 空数据绑定的ViewDataBinding，使用`if(emptyBinding is XXXBinding){}`进行判断
     * @param msg 当加载空布局数据时，根据emptyBinding进行提示。
     */
    fun emptyLayoutListener(activity: Activity, emptyBinding: ViewDataBinding, msg: String): Int

    /**
     * 错误布局
     *
     * @return Int 错误布局
     */
    @LayoutRes
    fun errorLayout(): Int

    /**
     * 错误通用事件处理
     *
     * 默认不会进行任何操作，提供给开发者自定义错误布局时，进行通用事件处理。
     * 一般用于显示错误信息，如网络错误，服务器错误等。
     *
     * @param activity 返回当前Activity，如果是Fragment则返回当前Fragment所在的Activity
     * @param errorBinding 错误绑定的ViewDataBinding，使用`if(errorBinding is XXXBinding){}`进行判断
     * @param msg 当加载错误布局数据时，根据errorBinding进行提示。
     *
     * @return id 重试按钮的id，用于点击后重新加载数据
     */
    @IdRes
    fun errorLayoutListener(activity: Activity, errorBinding: ViewDataBinding, msg: String): Int

}