package com.example.discovery.data.repository

import com.example.baselibrary.compose
import com.example.baselibrary.data.net.RetrofitFactory
import com.example.discovery.api.HotApi
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class RankRepository @Inject constructor() {

     fun requestRankList(apiUrl: String): Observable<HomeBean.Issue> {
        return RetrofitFactory.instance.create(HotApi::class.java).getIssueData(apiUrl).compose()
    }
}