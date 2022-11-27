package com.alvin.sample.custom

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.alvin.base_core.R

/**
 * <h3> 作用类描述：默认加载Dialog</h3>
 *
 * @Package :        com.alvin.base_core.base.dialog
 * @Date :           2022/11/19
 * @author 高国峰
 *
 * @param context 上下文
 * @param msg 提示信息
 * @param cancelable 是否可以取消
 * @param themeResId 主题
 */
class CustomLoadingDialog(
    context: Context,
    private val msg: String,
    private val cancelable: Boolean = false,
    @StyleRes themeResId: Int = R.style.BaseEngineDefaultRootLoadingDialog
) : Dialog(context, themeResId) {

    private val tvMsg by lazy { findViewById<AppCompatTextView>(R.id.tv_msg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.alvin.sample.R.layout.custom_loading_dialog)
        setCancelable(cancelable)
        val ivLoading = findViewById<AppCompatImageView>(R.id.iv_loading)
        tvMsg.text = msg
        val rotateDrawable = ivLoading.background as RotateDrawable
        ObjectAnimator.ofInt(rotateDrawable, "level", 0, 20000).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    /**
     * 修改内容
     *
     * @param msg String 内容
     */
    fun changeMsg(msg: String) {
        if (isShowing) {
            tvMsg.text = msg
        }
    }

    /**
     * 修改颜色
     */
    fun changeColor(@ColorInt color: Int) {
        if (isShowing) {
            tvMsg.setTextColor(color)
        }
    }

}