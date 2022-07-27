package com.alvin.gf_android.base

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.alvin.base_core.base.BaseMVVMActivity
import com.alvin.base_core.base.view_model.BaseViewModel
import com.alvin.gf_android.databinding.LayoutGlobalTitleBinding

/**
 * <h3> 作用类描述：自定义BaseActivity</h3>
 *
 * @Package :        com.alvin.gf_android.base
 * @Date :           2022/7/27
 * @author 高国峰
 */
abstract class BaseGFActivity<VM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes private val contentLayoutRes: Int,
    private val defaultLoadingLayout: Boolean = false
) : BaseMVVMActivity<VM, DB>(contentLayoutRes) {

    val titleLayout by lazy {
        getTitleLayout<LayoutGlobalTitleBinding>()
    }

    /**
     * 设置标题名
     * @param title String
     */
    fun setTitleName(title: String) {
        titleLayout.tvTitle.text = title
    }

    /**
     * 设置返回Icon
     */
    fun setBackIcon(@DrawableRes resId: Int) {
        titleLayout.ibBack.setImageResource(resId)
    }

    /**
     * 设置MenuIcon
     */
    fun setMenuIcon(@DrawableRes resId: Int, onClickListener: View.OnClickListener? = null) {
        titleLayout.ibRight.setImageResource(resId)
        titleLayout.ibRight.visibility = View.VISIBLE
        titleLayout.ibRight.setOnClickListener(onClickListener)
    }

    /**
     * 设置Menu文本
     */
    fun setMenuText(text: String, onClickListener: View.OnClickListener? = null) {
        titleLayout.tvRight.text = text
        titleLayout.tvRight.visibility = View.VISIBLE
        titleLayout.tvRight.setOnClickListener(onClickListener)
    }
}