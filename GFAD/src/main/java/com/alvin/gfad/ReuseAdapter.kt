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
    private var _onBind: (BaseViewHolder.() -> Unit)? = null

    /**
     * 添加指定模型布局
     *
     * 实体类.(索引) -> 布局ID
     *  Any.(Int) -> Int
     */
    var typeLayouts = mutableMapOf<Class<*>, Any.(Int) -> Int>()

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
     * 添加模型数据
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
     * 添加模型数据
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
    fun isRefresh(size: Int) {
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
         * 通过ID查找View
         * @param id Int ID
         * @return (V..V?)
         */
        fun <V : View> findView(@IdRes id: Int) = itemView.findViewById<V>(id)
    }

}