package com.alvin.gf_android.utils

import android.widget.Toast
import com.alvin.gf_android.App

/**
 * <h3> 作用类描述：</h3>
 *
 * @Package :        com.alvin.gf_android.utils
 * @Date :           2022/7/27
 * @author 高国峰
 */
fun String.toast() {
    Toast.makeText(App.instance, this, Toast.LENGTH_SHORT).show()
}