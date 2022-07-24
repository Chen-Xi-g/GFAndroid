package com.alvin.base_core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * <h3> 作用类描述：抽象的Activity,抽象声明必要的函数</h3>
 *
 * @Package :        com.alvin.base_core.base
 * @Date :           2022/7/23
 * @author 高国峰
 */
abstract class AbstractActivity : AppCompatActivity() {


    /**
     * 非特殊要求不建议重写.
     * 该函数在onCreate()之后,[initView]之前调用.
     * 该函数会对`dataBinding`进行初始化操作.
     */
    abstract fun initBinding()

    /**
     * 抽象声明`initView()`,用于初始化UI相关操作.
     * 该函数在布局设置完成之后调用.
     * 在函数中只进行UI相关的操作,建议不要进行**数据**相关的操作.
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 抽象声明`obtainData()`,用于初始化数据相关操作.
     * 该函数在 [initView] 函数之后调用.
     * 在函数中只进行数据相关的操作,建议不要进行 **视图初始化** 的相关的操作.
     */
    abstract fun obtainData()

    /**
     * 抽象声明`registerObserver()`,用于注册相关观察者.
     * 该函数在 [initBinding] 中调用
     * 在函数中只进行观察者的注册,禁止其他任何操作.
     */
    abstract fun registerObserver()
}