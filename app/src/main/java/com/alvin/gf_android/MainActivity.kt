package com.alvin.gf_android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.alvin.base_core.base.BaseMVVMActivity
import com.alvin.base_core.databinding.LayoutGfAndroidMvvmBaseTitleBinding
import com.alvin.base_core.model.BarModel
import com.alvin.gf_android.databinding.ActivityMainBinding

class MainActivity :
    BaseMVVMActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main, true) {

    private val baseTitleLayout by lazy(LazyThreadSafetyMode.NONE) {
        getTitleLayout<LayoutGfAndroidMvvmBaseTitleBinding>()
    }

    private val viewModel2 by lazy {
        ViewModelProvider(this).get(MainViewModel2::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        baseTitleLayout.tvTitle.text = "GF Android"
        baseTitleLayout.ibBack.setImageResource(android.R.drawable.ic_menu_add)
        baseTitleLayout.tvRight.text = "Add"
        baseTitleLayout.tvRight.visibility = View.VISIBLE
        baseTitleLayout.tvRight.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle("缺省页选择")
                .setItems(arrayOf("加载中", "内容", "错误", "空数据", "viewModel2内容")) { dialog, which ->
                    when (which) {
                        0 -> {
                            showLoadingLayout()
                        }
                        1 -> {
                            viewModel.getSuccess()
                        }
                        2 -> {
                            viewModel.getFail()
                        }
                        3 -> {
                            viewModel.getEmpty()
                        }
                        4 -> {
                            viewModel2.getSuccess()
                        }
                    }
                    dialog.dismiss()
                }.create()
            dialog.show()
        }
        baseTitleLayout.root.setBackgroundResource(R.color.purple_200)
        addLoadingObserve(viewModel2)
    }

    override fun obtainData() {
        viewModel.getSuccess()
    }

    override fun registerObserver() {
        viewModel.testData.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun setTitleLayout(): Int =
        com.alvin.base_core.R.layout.layout_gf_android_mvvm_base_title

    override fun setBar(): BarModel = BarModel(R.color.purple_200, false, R.color.purple_500, false)

    override fun onErrorLayoutRetryClick() {
        super.onErrorLayoutRetryClick()
        viewModel.getSuccess()
    }

}