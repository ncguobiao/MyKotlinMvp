package com.example.discovery.mvp.service

import com.example.hot.data.bean.TabInfoBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/7.
 * 热门 Model
 */

/**
 * 获取 TabInfo
 */
interface HotTabService {
    fun getTabInfo(): Observable<TabInfoBean>
}