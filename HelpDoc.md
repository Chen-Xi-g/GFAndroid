## 目录

* [Activity属性](#Activity属性)
* [GFAD属性](#GFAD属性)

## Activity属性

> 更加快速的使用DataBinding和ViewModel进行开发，以及一些常用的函数集成，提高开发效率.

### [AbstractActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/AbstractActivity.kt)

| 函数               | 说明                                                         |
| ------------------ | ------------------------------------------------------------ |
| initBinding()      | 对`dataBinding`进行初始化操作，在onCreate()之后，[initView]之前调用。 |
| initView()         | 在布局设置完成之后调用，只进行UI相关的操作，建议不要进行**数据**相关的操作。 |
| obtainData()       | 在 `initView` 函数之后调用，只进行数据相关的操作，建议不要进行 **视图初始化** 的相关的操作。 |
| registerObserver() | 在 `initBinding` 中调用，用于ViewModel数据观察。             |

### [BaseActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseActivity.kt)

| 变量             | 说明                         |
| ---------------- | ---------------------------- |
| iActivitySetting | 获取默认的Activity全局设置。 |
| isResume         | 当前Activity是否可见。       |
| isDestroy        | 当前Activity是否销毁。       |

| 函数                  | 说明                                                         |
| --------------------- | ------------------------------------------------------------ |
| setBar()              | 初始化状态栏，设置状态栏颜色或者透明.返回值为[BarModel](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/model/BarModel.kt)。 |
| softInputHideOrShow() | 显示隐藏软键盘。                                             |

### [BaseDialogActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseDialogActivity.kt)

| 函数        | 说明                     |
| ----------- | ------------------------ |
| setDialog() | 设置自定义的Dialog实现。 |
| dialog<T>() | 通过泛型获取Dialog。     |
| loading()   | 显示Dialog。             |
| dismiss()   | 隐藏Dialog。             |

### [BaseLayoutActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseLayoutActivity.kt)

| 变量           | 说明           |
| -------------- | -------------- |
| baseRootLayout | 缺省页根布局。 |

| 标题函数函数        | 说明                         |
| ------------------- | ---------------------------- |
| isShowTitleLayout() | 是否显示标题布局。           |
| setTitleLayout()    | 重写方法,返回自定义标题。    |
| getTitleLayout<T>() | 通过泛型获取自定义标题布局。 |

| 内容布局函数        | 说明                                                |
| ------------------- | --------------------------------------------------- |
| showContentLayout() | 显示内容布局,同时隐藏`加载`、`错误`、`空数据`布局。 |
| getContentLayout()  | 通过泛型获取自定义内容布局。                        |

| 加载布局函数          | 说明                                                |
| --------------------- | --------------------------------------------------- |
| setLoadingLayout()    | 重写方法,返回自定义加载布局。                       |
| showLoadingLayout()   | 显示加载布局,同时隐藏`内容`、`错误`、`空数据`布局。 |
| getLoadingLayout<T>() | 通过泛型获取自定义加载布局。                        |

| 错误布局函数              | 说明                          |
| ------------------------- | ----------------------------- |
| onErrorLayoutRetryClick() | 错误布局点击重试回调。        |
| setErrorRetryId()         | 设置点击重试的ViewId。        |
| setErrorMessageId()       | 设置显示错误文本的ViewId。    |
| setErrorLayout()          | 重写方法,返回自定义错误布局。 |
| getErrorLayout<T>()       | 通过泛型获取自定义错误布局。  |
| showErrorLayout()         | 显示错误布局。                |
| showErrorToast()          | 显示错误吐司。                |

| 空布局函数          | 说明                            |
| ------------------- | ------------------------------- |
| setEmptyLayout()    | 重写方法,返回自定义空数据布局。 |
| getEmptyLayout<T>() | 通过泛型获取自定义空数据布局。  |
| showEmptyLayout()   | 显示空数据布局。                |

| 其他函数   | 说明                          |
| ---------- | ----------------------------- |
| setToast() | 重写该方法可以自行设置Toast。 |

### [BaseNetworkActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseNetworkActivity.kt)

| 函数            | 说明           |
| --------------- | -------------- |
| beforeNetwork() | 网络请求开始。 |
| afterNetwork()  | 网络请求结束。 |
| failed()        | 网络请求错误。 |

### [BaseVMActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseVMActivity.kt)

| 变量      | 说明                          |
| --------- | ----------------------------- |
| viewModel | 获取通过泛型传递的ViewModel。 |

| 函数                | 说明                                  |
| ------------------- | ------------------------------------- |
| addLoadingObserve() | ViewModel通过非泛型绑定时，注册监听。 |

### [BaseMVVMActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseMVVMActivity.kt)

| 变量    | 说明                        |
| ------- | --------------------------- |
| binding | 内容布局的ViewDataBinding。 |

## GFAD属性

> 对于适配器的工作，一般都是一些重复的工作，比如创建Adapter，选择布局，设置各种事件回调，还有状态处理.
>
>为了处理上面的问题，所以对Adapter进行封装，并且使用Kotlin扩展函数加速开发，减少重复性工作.

### ReuseAdapter

| 变量                   | 说明                                 |
| ---------------------- | ------------------------------------ |
| list                   | 返回当前Adapter设置的数据集。        |
| typeLayouts            | 已经设置的指定类型布局，适配多布局。 |
| headerList             | 头布局集合                           |
| footerList             | 尾布局集合                           |
| headerCount            | 头布局数量                           |
| footerCount            | 尾布局数量                           |
| shakeEnable            | 当前Adapter是否需要设置防抖。        |
| checkedPosition        | 已选择条目的Position                 |
| selectModel            | 设置当前选择模式                     |
| onStickyAttachListener | 吸顶监听                             |

| 函数                              | 说明                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| onBind()                          | 基于Lambda为`onBindViewHolder()`添加回调，用于对Item内容进行操作。 |
| onItemClick()                     | 添加ItemView的点击事件回调。                                 |
| onItemLongClick()                 | 添加ItemView的长按事件回调。                                 |
| onChecked()                       | 选择回调                                                     |
| addOnItemChildClickListener()     | 添加ItemView的子View的点击事件回调                           |
| addOnItemChildLongClickListener() | 添加ItemView的子View的长按事件回调                           |
| checkedAll()                      | 全选或取消全选                                               |
| setChecked()                      | 设置选中状态                                                 |
| checkedSwitch()                   | 切换选中状态                                                 |
| addHeader()                       | 添加头布局                                                   |
| setHeader()                       | 替换头布局                                                   |
| removeHeader()                    | 删除头布局                                                   |
| removeHeaderAll()                 | 删除所有头布局                                               |
| isHeader()                        | 是否为头布局                                                 |
| addFooter()                       | 添加尾布局                                                   |
| setFooter()                       | 替换尾布局                                                   |
| removeFooter()                    | 删除尾布局                                                   |
| removeFooterAll()                 | 删除所有尾布局                                               |
| isFooter()                        | 是否为尾布局                                                 |
| scrollRv()                        | RecyclerView滑动到指定位置                                   |
| addType<T>()                      | 添加指定类型布局，这里的泛型类型需要和Model类型一致，否则无法找到布局。 |
| getData<T>()                      | 获取模型数据                                                 |
| getDataOrNull()                   | 获取模型数据,如果没有则返回null                              |
| setData()                         | 设置数据                                                     |
| addData()                         | 新增数据                                                     |
| remove()                          | 删除数据                                                     |
| removeAt()                        | 删除数据                                                     |
| isCheckedAll()                    | 当前是否已经全选                                             |
| isSticky()                        | 是否为吸顶布局                                               |

### ReuseAdapter.BaseViewHolder

| 变量  | 说明         |
| ----- | ------------ |
| _item | 获取当前数据 |

| 函数                  | 说明                                                    |
| --------------------- | ------------------------------------------------------- |
| getBinding<T>()       | 获取当前ItemView的ViewDataBinding对象，没有则返回null。 |
| getHeaderBinding<T>() | 获取头布局的ViewDataBinding，没有则返回null。           |
| getFooterBinding<T>() | 获取底布局的ViewDataBinding，没有则返回null。           |
| getType()             | 获取当前类型。                                          |
| getItem<T>()          | 通过泛型获取指定数据类型。                              |
| getItemOrNull<T>()    | 获取当前类型数据，如果找不到则返回null。                |
| findView<V>()         | 通过ViewId返回指定泛型View。                            |
| expand()              | 展开Item。                                              |
| collapse()            | 收起Item。                                              |
| expandOrCollapse()    | 展开或收起Item。                                        |