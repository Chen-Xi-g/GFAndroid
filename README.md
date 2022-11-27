# GFAndroid 快速开发组件合集

> 本项目是一个快速开发组件合集，包含了一些常用的组件目的是为了让开发者能够快速的开发出一个完整的APP，而不用再去重复造轮子。
> 并且推荐其他作者的优秀开源项目。
>
> 个人开源和推荐的开源大部分都是对Kotlin支持友好的组件。



**组件导航**

* [开发框架](#base_core)
* [网络请求](#网络请求)
* [图片加载](#图片加载)
* [RecyclerView](RecyclerView)
* [图片选择器](#图片选择器)

## base_core

结合Jetpack，构建快速开发的MVVM框架。

项目使用Jetpack：LiveData、ViewModel、Lifecycle组件。

支持多状态布局切换：加载中、成功、失败、空数据。

支持`Activity/Fragment`全局UI配置、局部UI配置。

[使用文档](https://chen-xi-g.github.io/)

## 网络请求

[liangjingkanj](https://github.com/liangjingkanji)的[Net](https://github.com/liangjingkanji/Net)网络请求框架，使用`OkHttp`+`协成`，对Kotlin的特性支持非常友好，而且解决`Issues`速度特别快。

## 图片加载

协程图片加载库[coil](https://github.com/coil-kt/coil)。

## RecyclerView

[liangjingkanj](https://github.com/liangjingkanji)的[BRV](https://github.com/liangjingkanji/BRV)，使用`OkHttp`+`协成`，支持**多布局**、**头尾布局**、**点击防抖**、**分组**、**悬停**等功能，对Kotlin的特性支持非常友好，而且解决`Issues`速度特别快。

## 图片选择器

[PictureSelector](https://github.com/LuckSiege/PictureSelector)是一款针对Android平台下的图片选择器，支持从相册获取图片、视频、音频&拍照，支持裁剪(单图or多图裁剪)、压缩、主题自定义配置等功能。

## 视频播放

[DKVideoPlayer](https://github.com/Doikki/DKVideoPlayer)自由度高，封装MediaPlayer、ExoPlayer、IjkPlayer。模仿抖音并实现预加载，列表播放，悬浮播放，广告播放，弹幕，视频水印，视频滤镜。