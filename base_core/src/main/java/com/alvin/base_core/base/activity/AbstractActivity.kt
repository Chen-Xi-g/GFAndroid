package com.alvin.base_core.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * <h3> 作用类描述：抽象Activity</h3>
 *
 * @Package :        com.alvin.base_core.base.activity
 * @Date :           2022/11/18
 * @author 高国峰
 */
abstract class AbstractActivity : AppCompatActivity() {

    /**
     * 初始化DataBinding布局
     * 该函数在onCreate()之后,[initView]之前调用.
     * 非特殊要求不建议重写.
     */
    abstract fun initBinding()

    /**
     * 抽象声明`initView()`,用于初始化UI相关操作.
     * 该函数在布局设置完成之后调用.
     * 在函数中只进行UI相关的操作,建议不要进行**数据**相关的操作.
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 抽象声明`obtainData()`,用于数据相关操作.
     * 该函数在 [initView] 函数之后调用.
     * 在函数中只进行数据相关的操作,建议不要进行 **视图初始化** 的相关的操作.
     */
    abstract fun obtainData()

    /**
     * 抽象声明`registerObserver()`,用于注册相关观察者.
     * 在函数中只进行观察者的注册,禁止其他任何操作.
     */
    abstract fun registerObserver()

}