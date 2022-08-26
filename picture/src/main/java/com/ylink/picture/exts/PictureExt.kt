package com.ylink.picture.exts

import android.app.Activity
import androidx.annotation.StyleRes
import cc.shinichi.library.ImagePreview
import com.luck.picture.lib.PictureSelectionModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.luck.picture.lib.listener.OnVideoSelectedPlayCallback
import com.ylink.picture.R
import com.ylink.picture.utils.GlideEngine

/**
 * <h3> 作用类描述：选择图片的扩展函数</h3>
 *
 * @Package :        com.ylink.picture.exts
 * @Date :           2022/8/26-15:32
 * @author 高国峰
 */

/**
 * 在Activity快速选择图片
 *
 * @param chooseMode 选择类型：[PictureMimeType.ofAll]、[PictureMimeType.ofImage]、[PictureMimeType.ofVideo]
 * @param isOpenCamera 是否打开相机。默认false
 * @param block 函数快
 */
fun Activity.takePhoto(
    chooseMode: Int = PictureMimeType.ofAll(),
    isOpenCamera: Boolean = false,
    block: PictureSelectionModel.() -> Unit
) {
    PictureSelector.create(this)
        .run {
            if (isOpenCamera) {
                openCamera(chooseMode)
            } else {
                openGallery(chooseMode)
            }.apply(block)
        }
}

/**
 * 选择照片
 * @param activity Activity
 * @param themeStyleId 主题样式id
 * @param imageEngine 图片加载引擎
 * @param chooseMode 选择类型：[PictureMimeType.ofAll]、[PictureMimeType.ofImage]、[PictureMimeType.ofVideo]
 * @param data: List<LocalMedia>, 上次选择的照片
 * @param isGif 是否显示Gif。默认不显示
 * @param isCamera 是否显示拍照按钮。默认显示
 * @param maxSelectNum 图片最大选择数量。默认9张
 * @param maxVideoSelectNum 视频最大选择数量。默认9张
 * @param isWithVideoImage 图片视频是否可以同时选择。默认不可以
 * @param isEnableCrop 是否裁剪。默认不裁剪
 * @param isCrop 圆形裁剪。默认关闭
 * @param aspectX 裁剪比例X。默认1
 * @param aspectY 裁剪比例Y。默认1
 * @param isOpenGallery true 打开相册 false 打开摄像头。默认打开相册
 * @param recordVideoSecond 录制视频最长时间。默认5分钟
 * @param isZoom 是否可放大缩小图片。默认可放大缩小
 * @param videoMaxSecond 录制视频最长时间。默认5分钟
 * @param videoMinSecond 录制视频最短时间。默认5秒
 * @param compress 是否压缩。默认压缩
 * @param queryMimeTypeConditions 查询mimeType条件。默认JPEG、JPG、PNG、MP4
 * @param listener 返回监听
 */
fun takePhoto(
    activity: Activity,
    @StyleRes themeStyleId: Int = com.luck.picture.lib.R.style.picture_WeChat_style,
    imageEngine: ImageEngine? = GlideEngine.createGlideEngine(),
    videoEngine: OnVideoSelectedPlayCallback<LocalMedia>? = null,
    chooseMode: Int = PictureMimeType.ofAll(),
    data: List<LocalMedia>? = null,
    isGif: Boolean = false,
    isCamera: Boolean = true,
    maxSelectNum: Int = 9,
    maxVideoSelectNum: Int = 9,
    isWithVideoImage: Boolean = false,
    isEnableCrop: Boolean = false,
    isCrop: Boolean = false,
    aspectX: Int = 1,
    aspectY: Int = 1,
    isOpenGallery: Boolean = true,
    recordVideoSecond: Int = 60 * 5,
    isZoom: Boolean = true,
    videoMaxSecond: Int = 60 * 5,
    videoMinSecond: Int = 5,
    compress: Boolean = true,
    queryMimeTypeConditions: Array<String> = arrayOf(
        PictureMimeType.JPEG,
        PictureMimeType.JPG,
        PictureMimeType.PNG,
        PictureMimeType.MP4
    ),
    listener: OnResultCallbackListener<LocalMedia>
) {
    activity.takePhoto(chooseMode, isOpenGallery) {
        // 设置主题
        theme(themeStyleId)
        // 图片加载引擎
        imageEngine(imageEngine)
        // 查看视频引擎
        bindCustomPlayVideoCallback(videoEngine)
        // 已选择数据
        selectionData(data)
        // 是否显示gif
        isGif(isGif)
        // 是否显示拍照按钮
        isCamera(isCamera)
        // 图片最大选择数量
        maxSelectNum(maxSelectNum)
        // 视频最大选择数量
        maxVideoSelectNum(maxVideoSelectNum)
        // 图片视频是否可以同时选择
        isWithVideoImage(isWithVideoImage)
        // 是否裁剪
        isEnableCrop(isEnableCrop)
        // 圆形裁剪
        circleDimmedLayer(isCrop)
        // 裁剪比例
        withAspectRatio(aspectX, aspectY)
        // 录制视频最长时间
        recordVideoSecond(recordVideoSecond)
        // 监听返回结果
        forResult(listener)
        // 是否开启缩放功能
        isZoomAnim(isZoom)
        // 录制视频最长时间
        videoMaxSecond(videoMaxSecond)
        // 录制视频最短时间
        videoMinSecond(videoMinSecond)
        // 是否压缩
        isCompress(compress)
        // 查询mimeType条件
        queryMimeTypeConditions(*queryMimeTypeConditions)
    }
}

/**
 * 预览图片
 *
 * @param data 图片数据集合
 * @param index 当前图片的索引
 */
fun Activity.seePhoto(
    data: List<String>,
    index: Int = 0,
    enableClickClose: Boolean = true,
    enableDragClose: Boolean = true,
    enableUpDragClose: Boolean = true
) {
    ImagePreview.instance
        .setContext(this)
        .setIndex(index)
        .setImageList(data.toMutableList())
        .setEnableClickClose(enableClickClose)
        .setEnableDragClose(enableDragClose)
        .setEnableUpDragClose(enableUpDragClose)
        .start()
}