## 目录

* [Activity属性](#Activity属性)

## Activity属性

1. `AbstractActivity`抽象的Activity,抽象声明必要的函数
    1. 公共`变量` AND `常量`
        1. 无.
    2. `函数`
        1. `initBinding()`该函数会对`dataBinding`进行初始化操作,在onCreate()之后,[initView]之前调用.
        2. `initView()`该函数在布局设置完成之后调用,只进行UI相关的操作,建议不要进行**数据**相关的操作.
        3. `obtainData()`该函数在 [initView] 函数之后调用,只进行数据相关的操作,建议不要进行 **视图初始化** 的相关的操作.
2. `BaseActivity`基础的Activity设置
    1. 公共`变量` AND `常量`
        1. `iActivitySetting`获取默认的Activity全局设置.
        2. `isResume`当前Activity是否可见.
        3. `isDestroy`当前Activity是否销毁.
    2. `函数`
        1. 重写`setBar()`初始化状态栏,
           设置状态栏颜色或者透明.返回值为[BarModel](https://github.com/Chen-Xi-g/GFAndroid/blob/main/base_core/src/src/main/java/com/alvin/base_core/model/BarModel)
           .
        2. 调用`softInputHideOrShow()`显示隐藏软键盘.
3. `BaseDialogActivity`在Activity上显示Dialog
    1. 公共`变量` AND `常量`
        1. 无.
    2. `函数`
        1. `dialog<T>()`通过泛型获取Dialog.
        2. `loading()`显示Dialog.
        3. `dismiss()`隐藏Dialog.
4. `BaseLayoutActivity`布局进初始化
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
            2. `setErrorLayout()`重写方法,返回自定义错误布局.
            3. `getErrorLayout<T>()`通过泛型获取自定义错误布局.
            4. `showErrorLayout()`显示错误布局.
            5. `showErrorToast()`显示错误吐司.
        5. 空数据设置
            1. `setEmptyLayout()`重写方法,返回自定义空数据布局.
            2. `getEmptyLayout<T>()`通过泛型获取自定义空数据布局.
            3. `showEmptyLayout()`显示空数据布局.
        6. 其他
            1. `setToast()`重写该方法可以自行设置Toast.
5. `BaseNetworkActivity`网络回调
    1. 公共`变量` AND `常量`
        1. 无.
    2. `函数`
        1. `beforeNetwork()`网络请求开始.
        2. `afterNetwork()`网络请求结束.
        3. `failed()`网络请求错误.
6. `BaseNetworkActivity`网络回调
    1. 公共`变量` AND `常量`
        1. 无
    2. `函数`
        1. `beforeNetwork()`网络请求开始
        2. `afterNetwork()`网络请求结束
        3. `failed()`网络请求失败
7. `BaseVMActivity`实现ViewModel的Activity基类
    1. 公共`变量` AND `常量`
        1. `viewModel`获取通过泛型传递的ViewModel.
    2. `函数`
        1. `addLoadingObserve()` ViewModel通过非泛型绑定时，注册监听.
8. `BaseMVVMActivity`所有Activity最终需要继承的MVVM类
    1. 公共`变量` AND `常量`
        1. `binding`内容布局的ViewDataBinding
    2. `函数`
        1. 无