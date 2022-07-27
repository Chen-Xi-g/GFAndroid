package com.alvin.gf_android.vm

import androidx.lifecycle.viewModelScope
import com.alvin.base_core.base.livedata.EventLiveData
import com.alvin.base_core.base.view_model.BaseViewModel
import com.alvin.base_core.model.NetworkModel
import com.alvin.gf_android.model.NormalEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <h3> 作用类描述：普通的ListViewModel</h3>
 *
 * @Package :        com.alvin.gf_android.vm
 * @Date :           2022/7/27
 * @author 高国峰
 */
class NormalViewModel : BaseViewModel() {

    private val _listData by lazy(LazyThreadSafetyMode.NONE) {
        EventLiveData<List<NormalEntity>>()
    }
    val listData: EventLiveData<List<NormalEntity>> = _listData

    /**
     * 获取数据
     */
    fun getTestData() {
        viewModelScope.launch {
            // 模拟网络请求,延迟2秒
            httpCallback.beforeNetwork.value = NetworkModel()
            delay(2000)
            val list = mutableListOf(
                NormalEntity(
                    1,
                    "标题1",
                    "哈哈哈原谅我不厚道的笑了",
                    "http://cdn.u2.huluxia.com/g3/M00/36/56/wKgBOVwPmdqAe61PAABw0fzQb_4559.jpg"
                ),
                NormalEntity(
                    2,
                    "标题2",
                    "从未见过面的网上女友说想要知道我长什么样子。我把我们寝室六人的合影发给了她，并自豪的说：里面最帅的那个人就是我。结果她猜到第六遍才猜对",
                    "http://cdn.u2.huluxia.com/g3/M01/36/57/wKgBOVwPme-AP4TJAACOiOZ2ODI192.jpg"
                ),
                NormalEntity(
                    3,
                    "标题3",
                    "公共汽车上老太太怕坐过站逢站必问。汽车到一站她就一个劲地用雨伞捅司机：“这是展览中心吗?”“不是，这是排骨!",
                    "http://cdn.u2.huluxia.com/g3/M00/36/56/wKgBOVwPmcmAB2cnAACcXKrjLlw989.jpg"
                ),
                NormalEntity(
                    4,
                    "标题4",
                    "那天逛商场，听见一个女售货员大声喊，四折促销皮鞋限时一小时，走过去突然听见她小声说，累死我了，都喊两小时了。",
                    "http://cdn.u2.huluxia.com/g3/M01/36/6B/wKgBOVwPn8SAfHRcAAC046awE3Q885.jpg"
                ),
                NormalEntity(
                    5,
                    "标题5",
                    "男友：亲爱的，你知道鱼为什么都是哑巴吗?女友：不知道。男友：很简单，你只要把头放到水里，试着说几句话就明白了",
                    "http://cdn.u2.huluxia.com/g3/M02/36/6C/wKgBOVwPoAmASl2jAADug4ZLXoo747.jpg"
                ),
                NormalEntity(
                    6,
                    "标题6",
                    "和朋友聊天，谈到你，我和他们吵了起来，还差点动手打起来，因为他们有的说你像猴子，有的说你像猩猩，实在太过分了!根本没有把你当猪看!",
                    "http://cdn.u2.huluxia.com/g3/M00/36/69/wKgBOVwPn0SACTLiAACn5zSRv_4777.jpg"
                ),
                NormalEntity(
                    7,
                    "标题7",
                    "男友下班回到家里，看到桌子上有妻子留的字条：“亲爱的，我上女伴家里去了，晚餐吃鱼，钓鱼竿在门后。",
                    "http://cdn.u2.huluxia.com/g3/M03/36/6F/wKgBOVwPoL2AFamgAAMMDfq9_Xk002.png"
                ),
                NormalEntity(
                    8,
                    "标题8",
                    "老师：“你来说说你的理想是什么?” 小学生：“吃得好，穿得好，过得好。” 老师：“你的理想能不能更高些?” 小学生：“吃得更好，穿得更好，过得更好。”",
                    "http://cdn.u2.huluxia.com/g3/M02/36/6D/wKgBOVwPoGWAFKEAAAO6Pqn7Vgc449.jpg"
                ),
                NormalEntity(
                    9,
                    "标题9",
                    "中午下班和同事去吃饭，路边有一大汉头破血流盘腿而做，我就问同事这是咋了?同事：“没钱买药，坐地上回血呢。",
                    "http://cdn.u2.huluxia.com/g3/M00/36/56/wKgBOVwPmcmAB2cnAACcXKrjLlw989.jpg"
                ),
                NormalEntity(
                    10,
                    "标题10",
                    "今天打赌输了，帮女同桌买卫生锦，刚要进教室，被老师看到了。问我是什么?我机智的说了一句：是面包。老师甩了我一句：站外面吃完再进来。",
                    "http://cdn.u2.huluxia.com/g3/M03/36/6E/wKgBOVwPoJyACseCAADB51b8Who116.jpg"
                )
            )
            _listData.value = list
            httpCallback.afterNetwork.value = true
        }
    }

}