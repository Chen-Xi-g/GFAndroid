## 目录

* [Activity属性](#Activity属性)
* [GFAD属性](#GFAD属性)

## Activity属性

> 更加快速的使用DataBinding和ViewModel进行开发，以及一些常用的函数集成，提高开发效率.

1. [AbstractActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/AbstractActivity.kt)抽象的Activity,抽象声明必要的函数
    1. 公共`变量` AND `常量`
        1. 无.
    2. `函数`
        1. `initBinding()`该函数会对`dataBinding`进行初始化操作,在onCreate()之后,[initView]之前调用.
        2. `initView()`该函数在布局设置完成之后调用,只进行UI相关的操作,建议不要进行**数据**相关的操作.
        3. `obtainData()`该函数在 `initView` 函数之后调用,只进行数据相关的操作,建议不要进行 **视图初始化** 的相关的操作.
        4. `registerObserver()`该函数在 `initBinding` 中调用,用于ViewModel数据观察.
2. [BaseActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseActivity.kt)基础的Activity设置
    1. 公共`变量` AND `常量`
        1. `iActivitySetting`获取默认的Activity全局设置.
        2. `isResume`当前Activity是否可见.
        3. `isDestroy`当前Activity是否销毁.
    2. `函数`
        1. 重写`setBar()`初始化状态栏,
           设置状态栏颜色或者透明.返回值为[BarModel](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/model/BarModel.kt).
        2. 调用`softInputHideOrShow()`显示隐藏软键盘.
3. [BaseDialogActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseDialogActivity.kt)在Activity上显示Dialog
    1. 公共`变量` AND `常量`
        1. 无.
    2. `函数`
        1. `setDialog()`设置自定义的Dialog实现.
        2. `dialog<T>()`通过泛型获取Dialog.
        3. `loading()`显示Dialog.
        4. `dismiss()`隐藏Dialog.
4. [BaseLayoutActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseLayoutActivity.kt)布局进初始化
    1. 公共`变量` AND `常量`
        1. `baseRootLayout`缺省页根布局.
    2. `函数`
        1. 标题设置
            1. `isShowTitleLayout()`是否显示标题布局.
            2. `setTitleLayout()`重写方法,返回自定义标题.
            3. `getTitleLayout<T>()`通过泛型获取自定义标题布局.
        2. 内容设置
            1. `showContentLayout()`显示内容布局,同时隐藏`加载`、`错误`、`空数据`布局.
            2. `getContentLayout()`通过泛型获取自定义内容布局.
        3. 加载设置
            1. `setLoadingLayout()`重写方法,返回自定义加载布局.
            2. `showLoadingLayout()`显示加载布局,同时隐藏`内容`、`错误`、`空数据`布局.
            3. `getLoadingLayout<T>()`通过泛型获取自定义加载布局.
        4. 错误设置
            1. `onErrorLayoutRetryClick()`错误布局点击重试回调.
            2. `setErrorRetryId()`设置点击重试的ViewId.
            3. `setErrorMessageId()`设置显示错误文本的ViewId
            4. `setErrorLayout()`重写方法,返回自定义错误布局.
            5. `getErrorLayout<T>()`通过泛型获取自定义错误布局.
            6. `showErrorLayout()`显示错误布局.
            7. `showErrorToast()`显示错误吐司.
        5. 空数据设置
            1. `setEmptyLayout()`重写方法,返回自定义空数据布局.
            2. `getEmptyLayout<T>()`通过泛型获取自定义空数据布局.
            3. `showEmptyLayout()`显示空数据布局.
        6. 其他
            1. `setToast()`重写该方法可以自行设置Toast.
5. [BaseNetworkActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseNetworkActivity.kt)网络回调
    1. 公共`变量` AND `常量`
        1. 无.
    2. `函数`
        1. `beforeNetwork()`网络请求开始.
        2. `afterNetwork()`网络请求结束.
        3. `failed()`网络请求错误.
6. [BaseVMActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseVMActivity.kt)实现ViewModel的Activity基类
    1. 公共`变量` AND `常量`
        1. `viewModel`获取通过泛型传递的ViewModel.
    2. `函数`
        1. `addLoadingObserve()` ViewModel通过非泛型绑定时，注册监听.
7. [BaseMVVMActivity](https://github.com/Chen-Xi-g/GFAndroid/blob/master/base_core/src/main/java/com/alvin/base_core/base/BaseMVVMActivity.kt)所有Activity最终需要继承的MVVM类.
    1. 公共`变量` AND `常量`
        1. `binding`内容布局的ViewDataBinding.
    2. `函数`
        1. 无.

## GFAD属性

>对于适配器的工作，一般都是一些重复的工作，比如创建Adapter，选择布局，设置各种事件回调，还有状态处理.
>
>为了处理上面的问题，所以对Adapter进行封装，并且使用Kotlin扩展函数加速开发，减少重复性工作.

### ReuseAdapter

1. 公共变量
    1. `list`返回当前Adapter设置的数据集.
    2. `typeLayouts`已经设置的指定类型布局,适配多布局.
    3. `shakeEnable`当前Adapter是否需要设置防抖
    4. `checkedPosition`已选择条目的Position
    5. `selectModel`设置当前选择模式
2. 函数
    1. `onBind()`基于Lambda为`onBindViewHolder()`添加回调,用于对Item内容进行操作.
    2. `addType<T>()`添加指定类型布局, 这里的泛型类型需要和Model类型一致, 否则无法找到向右布局.
    3. `onItemClick()`为Recyclerview添加ItemView的点击事件回调.
    4. `onItemLongClick()`为Recyclerview添加ItemView的长按事件回调.
    5. `onChecked()`选择回调.
    6. `onStickyAttachListener`吸顶监听.
    7. `addOnItemChildClickListener()`为Recyclerview添加ItemView的子View的点击事件回调.
    8. `addOnItemChildLongClickListener()`为Recyclerview添加ItemView的子View的长按事件回调.
    9. `checkedAll()`全选或取消全选.
    10. `setChecked()`设置指定索引选中状态.
    11. `checkedSwitch()`切换指定索引选中状态.
    12. `getData<T>()`根据索引获取模型数据.
    13. `setData()`设置数据.
    14. `addData()`新增数据.
    15. `removeAt()`删除数据.
    16. `isCheckedAll()`当前是否已经全选.
    17. `isSticky()`判断指定索引是否为吸顶布局

### ReuseAdapter.BaseViewHolder

1. 公共变量
    1. `_item`获取当前Item的数据.
2. 函数
    1. `getBinding<T>()`获取当前ItemView的ViewDataBinding对象.
    2. `getType()`获取当前类型.
    3. `getItem<T>()`通过泛型获取指定数据类型.
    4. `getItemOrNull<T>()`获取当前类型数据, 如果找不到则返回null.
    5. `findView<V>()`通过ViewId返回指定泛型View.
    6. `expand()`展开Item.
    7. `collapse()`收起Item.
    8. `expandOrCollapse()`展开或收起Item.