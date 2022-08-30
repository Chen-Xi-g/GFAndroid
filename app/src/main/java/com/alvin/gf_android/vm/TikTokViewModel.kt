package com.alvin.gf_android.vm

import androidx.lifecycle.viewModelScope
import com.alvin.base_core.base.livedata.EventLiveData
import com.alvin.base_core.base.view_model.BaseViewModel
import com.alvin.gf_android.model.TikTokEntityI
import com.alvin.gf_android.utils.CommonUIConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * <h3> 作用类描述：抖音列表ViewModel</h3>
 *
 * @Package :        com.alvin.gf_android.vm
 * @Date :           2022/8/27
 * @author 高国峰
 */
class TikTokViewModel : BaseViewModel() {

    /**
     * 拼接视频数据
     */
    val videoList by lazy(LazyThreadSafetyMode.NONE) {
        EventLiveData<List<TikTokEntityI>>()
    }

    fun getVideoList() {
        viewModelScope.launch {
            videoList.value = pictureData()
        }
    }

    /**
     * 合并数据
     */
    private suspend fun pictureData(): List<TikTokEntityI> {
        return withContext(Dispatchers.IO) {
            val imgList = async { CommonUIConstant.imgList }
            val videoList = async { CommonUIConstant.videoList }
            val pictureList = mutableListOf<TikTokEntityI>()
            videoList.await().forEachIndexed { index, item ->
                pictureList.add(
                    TikTokEntityI(
                        videoUrl = item,
                        videoThumbUrl = imgList.await()[index],
                        videoTitle = "标题$index"
                    )
                )
            }
            pictureList
        }
    }

}