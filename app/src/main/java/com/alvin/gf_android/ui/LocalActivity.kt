package com.alvin.gf_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alvin.base_core.base.BaseMVVMActivity
import com.alvin.base_core.helper.IDialogSetting
import com.alvin.base_core.model.BarModel
import com.alvin.gf_android.R
import com.alvin.gf_android.databinding.ActivityLocalBinding
import com.alvin.gf_android.databinding.LayoutLocalTitleBinding
import com.alvin.gf_android.setting.LocalSettingDialog
import com.alvin.gf_android.utils.toast
import com.alvin.gf_android.vm.LocalViewModel

/**
 * <h3> 作用类描述：局部Activity设置</h3>
 *
 * @Package :        com.alvin.gf_android.ui
 * @Date :           2022/7/27
 * @author 高国峰
 */
class LocalActivity :
    BaseMVVMActivity<LocalViewModel, ActivityLocalBinding>(R.layout.activity_local) {

    companion object {
        fun newIntent(context: Context) = Intent(context, LocalActivity::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        getTitleLayout<LayoutLocalTitleBinding>().let {
            it.tvTitle.text = "局部设置"
            it.tvRight.text = "局部Menu"
            it.tvRight.setOnClickListener {
                "Menu".toast()
            }
            it.tvRight.visibility = View.VISIBLE
        }
        showErrorLayout()
    }

    override fun obtainData() {
    }

    override fun registerObserver() {
    }

    override fun onErrorLayoutRetryClick() {
        super.onErrorLayoutRetryClick()
        showLoadingLayout()
    }

    override fun setBar(): BarModel = BarModel(R.color.white, true, R.color.black, false)

    override fun setDialog(): IDialogSetting = LocalSettingDialog()

    override fun setTitleLayout(): Int = R.layout.layout_local_title

    override fun isShowTitleLayout(): Boolean = true

    override fun setLoadingLayout(): Int = R.layout.layout_local_loading

    override fun setErrorLayout(): Int = R.layout.layout_local_error

    override fun setErrorRetryId(): Int = R.id.tvLocalClickRetry

    override fun setErrorMessageId(): Int = R.id.tvLocalError

    override fun setEmptyLayout(): Int = R.layout.layout_local_empty
}