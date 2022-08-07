package com.alvin.base_core.helper

/**
 * <h3> 作用类描述：对框架进行全局设置</h3>
 *
 * @Package :        com.alvin.base_core.helper
 * @Date :           2022/7/23
 * @author 高国峰
 */
object GlobalUIBuilder {
    /**
     * 加载默认的 Activity 设置
     *
     */
    var iSettingBaseActivity: IActivitySetting = DefaultSettingActivity()

    /**
     * 加载默认的 Fragment 设置
     *
     */
    var iSettingBaseFragment: IFragmentSetting = DefaultSettingFragment()

    /**
     * 全局初始化
     *
     * @param iSettingBaseActivity 自定义对全局Activity的设置
     * @param iSettingBaseActivity 自定义对全局Activity依赖的Dialog进行设置
     */
    fun initSetting(
        iSettingBaseActivity: IActivitySetting = DefaultSettingActivity(),
        iSettingBaseFragment: IFragmentSetting = DefaultSettingFragment()
    ) {
        GlobalUIBuilder.iSettingBaseActivity = iSettingBaseActivity
        GlobalUIBuilder.iSettingBaseFragment = iSettingBaseFragment
    }
}