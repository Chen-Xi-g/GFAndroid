package com.alvin.gfad

import android.annotation.SuppressLint
import android.util.NoSuchPropertyException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alvin.gfad.mode.ICheckedEntity
import com.alvin.gfad.mode.SelectSealed
import java.util.concurrent.atomic.AtomicLong

/**
 * <h3> 作用类描述：RecyclerView 可重复利用的适配器</h3>
 *
 * @Package :        com.alvin.gfad
 * @Date :           2022/7/24
 * @author 高国峰
 */
@SuppressLint("NotifyDataSetChanged")
open class ReuseAdapter : RecyclerView.Adapter<ReuseAdapter.BaseViewHolder>() {

    /**
     * 模型数据数据
     */
    private var _list: MutableList<Any?> = mutableListOf()
    val list = _list

    /**
     * 回调
     */
    // onBindViewHolder回调
    private var _onBind: (BaseViewHolder.() -> Unit)? = null

    // 点击事件回调
    private var _onItemClick: (BaseViewHolder.(view: View) -> Unit)? =
        null

    // 长按事件回调
    private var _onItemLongClick: (BaseViewHolder.(view: View) -> Unit)? =
        null

    // 单选多选回调
    private var onChecked: ((position: Int, checked: Boolean, allChecked: Boolean) -> Unit)? =
        null

    // 子Item点击事件回调集合, key为子Item的id, value为回调
    private val clickListeners = mutableMapOf<Int, (BaseViewHolder.(viewId: Int) -> Unit)>()

    // 子Item长按事件回调集合, key为子Item的id, value为回调
    private val longClickListeners = mutableMapOf<Int, (BaseViewHolder.(viewId: Int) -> Unit)>()

    /**
     * 添加指定类型布局,适配多布局.
     *
     * 实体类.(索引) -> 布局ID
     *  Any.(Int) -> Int
     */
    var typeLayouts = mutableMapOf<Class<*>, Any.(Int) -> Int>()

    /**
     * 是否需要为点击事件设置防抖,默认为true.
     */
    var isShake = true

    /**
     * 已选择条目的Position
     */
    val checkedPosition = mutableListOf<Int>()

    /**
     * 当前选择模式
     * 默认选择模式为 [SelectSealed.None] 什么都不做.
     * 可以项:
     * [SelectSealed.Single] 单选
     * [SelectSealed.Single] 多选
     */
    var selectModel: SelectSealed = SelectSealed.None

    /** 已选择条目数量 */
    val checkedCount: Int get() = checkedPosition.size

    /**
     * 是否全选
     */
    fun isCheckedAll(): Boolean = checkedCount == _list.size

    override fun getItemViewType(position: Int): Int {
        val item = getData<Any>(position)
        return typeLayouts[item.javaClass]?.invoke(item, position) ?: throw NoSuchPropertyException(
            "没有找到该类型属性"
        )
    }

    /**
     * 创建ViewHolder
     *
     * @param parent ViewGroup
     * @param viewType Int 这里返回布局ID
     * @return BaseViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(getData(position))
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    /**
     * [onBindViewHolder] 回调
     *
     * @param onBind [@kotlin.ExtensionFunctionType] Function1<BaseViewHolder, Unit>
     */
    fun onBind(onBind: (BaseViewHolder.() -> Unit)) {
        _onBind = onBind
    }

    /**
     * 为Recyclerview添加ItemView的点击事件回调
     */
    fun onItemClick(onItemClick: (BaseViewHolder.(view: View) -> Unit)) {
        _onItemClick = onItemClick
    }

    /**
     * 为Recyclerview添加ItemView的长按事件回调
     */
    fun onItemLongClick(onItemLongClick: (BaseViewHolder.(view: View) -> Unit)) {
        _onItemLongClick = onItemLongClick
    }

    /**
     * 为Recyclerview添加ItemView的子View的点击事件回调
     */
    fun addOnItemChildClickListener(
        @IdRes vararg id: Int,
        onItemChildClick: (BaseViewHolder.(viewId: Int) -> Unit)
    ) {
        id.forEach {
            clickListeners[it] = onItemChildClick
        }
    }

    /**
     * 为Recyclerview添加ItemView的子View的长按事件回调
     */
    fun addOnItemChildLongClickListener(
        @IdRes vararg id: Int,
        onItemChildLongClick: (BaseViewHolder.(viewId: Int) -> Unit)
    ) {
        id.forEach {
            longClickListeners[it] = onItemChildLongClick
        }
    }

    /**
     * 单选模式下不可全选, 但可以取消单选
     * @param checked true为全选, false 取消全部选择
     */
    fun checkedAll(checked: Boolean = true) {
        if (isCheck()) return
        if (checked) {
            // 全选
            if (selectModel is SelectSealed.Single) return
            _list.forEachIndexed { index, t ->
                if (!checkedPosition.contains(index)) {
                    setChecked(index, true)
                }
            }
        } else {
            // 取消全选
            _list.forEachIndexed { index, t ->
                if (checkedPosition.contains(index)) setChecked(index, false)
            }
        }
    }

    /**
     * 设置选中
     *
     * @param position 当前索引
     * @param checked true: 选中,  false: 取消选中
     */
    fun setChecked(position: Int, checked: Boolean) {
        // 避免冗余操作
        if ((checkedPosition.contains(position) && checked) ||
            (!checked && !checkedPosition.contains(position)) || isCheck()
        ) return

        // 实体类没有实现 ICheckedEntity 接口,直接return
        val item = getData<Any>(position)
        if (item !is ICheckedEntity) return

        if (checked) checkedPosition.add(position)
        else checkedPosition.remove(position)

        if (selectModel is SelectSealed.Single && checked && checkedPosition.size > 1) {
            // 当前模式为单选, 使用递归取消选择
            setChecked(checkedPosition[0], false)
        }
        // 修改选择状态
        item.isSelected = checked
        // 选择回调
        onChecked?.invoke(position, checked, isCheckedAll())
        notifyItemChanged(position)
    }

    /**
     * 切换选中
     */
    fun checkedSwitch(position: Int) {
        if (isCheck()) return
        if (checkedPosition.contains(position)) {
            setChecked(position, false)
        } else {
            setChecked(position, true)
        }
    }

    /**
     * 判断是否需要选中
     *
     * @return true: 需要选中, false: 不需要选中
     */
    private fun isCheck() = onChecked != null && selectModel !is SelectSealed.None

    /**
     * 选择回调
     */
    fun onChecked(block: (position: Int, checked: Boolean, allChecked: Boolean) -> Unit) {
        onChecked = block
    }

    /**
     * 添加指定类型布局, 这里的泛型类型需要和Model类型一致, 否则无法找到向右布局
     *
     * @param layoutRes Int
     */
    inline fun <reified T> addType(@LayoutRes layoutRes: Int) {
        typeLayouts[T::class.java] = { layoutRes }
    }

    /**
     * 添加指定类型布局, 这里的泛型类型需要和Model类型一致, 否则无法找到向右布局
     *
     * 实体类.(索引) 返回 布局ID
     */
    inline fun <reified T> addType(noinline block: T.(Int) -> Int) {
        typeLayouts[T::class.java] = block as Any.(Int) -> Int
    }

    /**
     * 根据索引获取模型数据
     */
    fun <T> getData(index: Int): T {
        return _list[index] as T
    }

    /**
     * 设置数据
     *
     * @param list List<*> 数据源
     */
    fun setData(list: List<*>?) {
        if (list !== _list) {
            _list.clear()
            if (!list.isNullOrEmpty()) {
                _list.addAll(list)
            }
        } else {
            _list.clear()
            if (!list.isNullOrEmpty()) {
                _list.addAll(list)
            }
        }
        notifyDataSetChanged()
    }

    /**
     * 更新指定索引数据
     *
     * @param index Int 索引
     * @param item 数据源
     */
    fun setData(index: Int, item: Any) {
        if (index < _list.size) {
            _list[index] = item
            notifyItemChanged(index)
            isRefresh(0)
        }
    }

    /**
     * 添加数据
     * @param data List<Any?>?
     * @param index Int 从指定索引添加数据
     */
    fun addData(data: List<Any?>, index: Int = -1) {
        if (index < _list.size) {
            if (index == -1) {
                _list.addAll(data)
                notifyItemRangeInserted(_list.size - data.size, data.size)
            } else {
                _list.addAll(index, data)
                notifyItemRangeInserted(index, data.size)
            }
            isRefresh(data.size)
        }
    }

    /**
     * 添加数据
     * @param item Any 数据源
     * @param index Int 从指定索引添加数据, 值为-1时在指定索引添加, 默认在最后添加.
     */
    fun addData(item: Any?, index: Int = -1) {
        if (index < _list.size) {
            if (index == -1) {
                _list.add(item)
                notifyItemInserted(_list.size - 1)
            } else {
                _list.add(index, item)
                notifyItemInserted(index)
            }
            isRefresh(0)
        }
    }

    /**
     * 删除指定索引数据
     *
     * @param index Int 索引
     */
    fun removeAt(index: Int) {
        if (index < _list.size) {
            _list.removeAt(index)
            notifyItemRemoved(index)
            isRefresh(0)
            notifyItemRangeChanged(index, _list.size - index)
        }
    }

    /**
     * 数据与指定size相同时刷新适配器
     *
     * @param size 新数据数量
     */
    private fun isRefresh(size: Int) {
        if (_list.size == size) {
            notifyDataSetChanged()
        }
    }

    inner class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        lateinit var _item: Any private set

        /**
         * 使用DataBinding后通过此方法获取ViewBinding
         *
         * @return DB?
         */
        fun <DB : ViewDataBinding> getBinding(): DB? {
            return DataBindingUtil.bind(itemView)
        }

        internal fun onBind(item: Any) {
            _item = item
            _onBind?.invoke(this)
            // 点击事件
            _onItemClick?.let { itemClick ->
                if (isShake) {
                    itemView.setOnClickListener(ShakeClickListener {
                        itemClick.invoke(this@BaseViewHolder, this)
                    })
                } else {
                    itemView.setOnClickListener {
                        itemClick.invoke(this@BaseViewHolder, it)
                    }
                }
            }
            // 长按事件
            _onItemLongClick?.let { longClick ->
                itemView.setOnLongClickListener {
                    longClick.invoke(this@BaseViewHolder, it)
                    true
                }
            }
            // 子Item点击事件
            for (clickListener in clickListeners) {
                val view = itemView.findViewById<View>(clickListener.key) ?: continue
                if (isShake) {
                    view.setOnClickListener(ShakeClickListener {
                        clickListener.value.invoke(this@BaseViewHolder, clickListener.key)
                    })
                } else {
                    view.setOnClickListener {
                        clickListener.value.invoke(this@BaseViewHolder, clickListener.key)
                    }
                }
            }
            // 子Item长按事件
            for (longClickListener in longClickListeners) {
                val view = itemView.findViewById<View>(longClickListener.key) ?: continue
                view.setOnLongClickListener {
                    longClickListener.value.invoke(this@BaseViewHolder, longClickListener.key)
                    true
                }
            }
        }

        /**
         * 获取当前类型
         * @return Any
         */
        fun getType() = _item

        /**
         * 获取当前类型数据
         */
        inline fun <reified T> getItem() = _item as T

        /**
         * 获取当前类型数据, 如果找不到则返回null
         */
        inline fun <reified T> getItemOrNull() = _item as? T

        /**
         * 通过ID查找View
         * @param id Int ID
         * @return (V..V?)
         */
        fun <V : View> findView(@IdRes id: Int) = itemView.findViewById<V>(id)
    }

    /**
     * 点击事件防抖
     *
     * @property internal
     * @property block
     */
    private class ShakeClickListener(
        private val internal: Int = 500,
        private val block: View.() -> Unit
    ) : View.OnClickListener {

        private val lastClickTime = AtomicLong(0)

        override fun onClick(v: View) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime.get() > internal) {
                lastClickTime.set(currentTime)
                block.invoke(v)
            }
        }
    }

}