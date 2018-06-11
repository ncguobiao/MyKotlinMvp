package com.example.discovery.data.repository

import com.example.baselibrary.compose
import com.example.baselibrary.data.net.RetrofitFactory
import com.example.discovery.api.HotApi
import com.example.hot.data.bean.TabInfoBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class HotTabRepository @Inject constructor() {

    /**
     * 获取全部排行榜的Info（包括，title 和 Url）
     */
     fun getTabInfo(): Observable<TabInfoBean> {
        return RetrofitFactory.instance.create(HotApi::class.java).getRankList().compose()
    }
}