package com.alvin.gfad.mode

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gfad.mode
 * @Date :           2022/7/24
 * @author 高国峰
 */
sealed class SelectSealed {

    /**
     * 什么都不做
     */
    object None : SelectSealed()

    /**
     * 单选模式密封类
     */
    object Single : SelectSealed()

    /**
     * 多选模式密封类
     */
    object Multiple : SelectSealed()
}