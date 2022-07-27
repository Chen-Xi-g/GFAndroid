package com.alvin.base_core.base

/**
 * <h3> 作用类描述：在Activity上显示Dialog</h3>
 *
 * @Package :        com.alvin.base_core.base
 * @Date :           2022/7/24
 * @author 高国峰
 */
abstract class BaseDialogActivity : BaseActivity() {

    /**
     * 获取Dialog
     */
    private val dialog by lazy(LazyThreadSafetyMode.NONE) {
        setDialog().dialog(this)
    }

    /**
     * 自定义局部Dialog
     * @return IDialogSetting
     */
    open fun setDialog() = iActivitySetting.dialogSetting()

    /**
     * 获取当前Dialog, 需要指定Dialog类型
     */
    fun <T> dialog(): T? {
        return dialog as? T
    }

    /**
     * 显示加载框的Dialog
     *
     * @param content loading 显示的内容
     */
    open fun loading(content: String) {
        dialog?.let {
            setDialog().show(it, content)
        }
    }

    /**
     * 关闭Dialog
     */
    open fun dismiss() {
        dialog?.let {
            setDialog().dismiss(it)
        }
    }

    override fun onStop() {
        super.onStop()
        dismiss()
    }
}