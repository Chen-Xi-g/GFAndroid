package com.alvin.gfad.layout_manager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * <h3> 作用类描述：自定义LayoutLinearManager，设置是否允许滑动</h3>
 *
 * @Package :        com.alvin.gfad.layout_manager
 * @Date :           2022/6/15
 * @author 高国峰
 */
class ReuseLinearLayoutManager : LinearLayoutManager {

    private var scrollEnabled = true

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        @RecyclerView.Orientation orientation: Int,
        reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    fun setScrollEnabled(enabled: Boolean): ReuseLinearLayoutManager {
        scrollEnabled = enabled
        return this
    }

    override fun canScrollVertically(): Boolean {
        return super.canScrollVertically() && scrollEnabled
    }

    override fun canScrollHorizontally(): Boolean {
        return super.canScrollHorizontally() && scrollEnabled
    }
}