package com.example.discovery.mvp.service

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/7.
 */
interface FollowService {

    fun requestFollowList(): Observable<HomeBean.Issue>

    fun loadMoreData(url:String): Observable<HomeBean.Issue>
}