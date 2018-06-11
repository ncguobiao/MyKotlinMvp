package com.example.hot.mvp.service

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/8.
 */
interface RankService {
    /**
     * 获取排行榜
     */
    fun requestRankList(apiUrl:String): Observable<HomeBean.Issue>
}