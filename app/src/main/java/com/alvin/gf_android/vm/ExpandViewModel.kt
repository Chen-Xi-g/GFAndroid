package com.alvin.gf_android.vm

import com.alvin.base_core.base.livedata.EventLiveData
import com.alvin.base_core.base.view_model.BaseViewModel
import com.alvin.gf_android.model.ExpandEntity

/**
 * <h3> 作用类描述：展开收起的ViewModel</h3>
 *
 * @Package :        com.alvin.gf_android.vm
 * @Date :           2022/7/30
 * @author 高国峰
 */
class ExpandViewModel : BaseViewModel() {

    private val _listData by lazy(LazyThreadSafetyMode.NONE) {
        EventLiveData<List<ExpandEntity>>()
    }
    val listData: EventLiveData<List<ExpandEntity>> = _listData

    fun onExpand() {
        val list = mutableListOf<ExpandEntity>()
        for (i in 0..10) {
            val childList = mutableListOf<Any>()
            for (j in 0..2) {
                val childChildList = mutableListOf<Any>()
                for (k in 0..2) {
                    val childChildChild = mutableListOf<Any>()
                    for (l in 0..2) {
                        childChildChild.add(
                            ExpandEntity(
                                i,
                                "子子子数据标题$i$j$k$l",
                                "子子子数据内容$i$j$k$l",
                                itemChildList = null
                            )
                        )
                    }
                    childChildList.add(
                        ExpandEntity(
                            i,
                            "子子数据标题$i$j$k",
                            "子子数据内容$i$j$k",
                            itemChildList = childChildChild
                        )
                    )
                }
                childList.add(
                    ExpandEntity(
                        i,
                        "子数据标题$i$j",
                        "子数据内容$i$j",
                        itemChildList = childChildList
                    )
                )
            }
            list.add(ExpandEntity(i, "展开收起的标题$i", "展开收起的内容$i", itemChildList = childList))
        }
        _listData.value = list.toList()
    }

}