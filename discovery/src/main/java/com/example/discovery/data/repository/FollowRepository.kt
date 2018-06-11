package com.example.discovery.data.repository

import com.example.baselibrary.compose
import com.example.baselibrary.data.net.RetrofitFactory
import com.example.discovery.api.DiscoveryApi
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class FollowRepository @Inject constructor() {

     fun requestFollowList(): Observable<HomeBean.Issue> {
        return RetrofitFactory.instance.create(DiscoveryApi::class.java).getFollowInfo().compose()
    }

     fun loadMoreData(url:String): Observable<HomeBean.Issue> {
         return RetrofitFactory.instance.create(DiscoveryApi::class.java).getIssueData(url).compose()
    }
}