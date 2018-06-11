package com.example.discovery.mvp.service.impl

import com.example.discovery.data.repository.FollowRepository
import com.example.discovery.mvp.service.FollowService
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class FollowServiceImpl @Inject constructor(): FollowService {
    @Inject
    lateinit var repository: FollowRepository

    override fun requestFollowList(): Observable<HomeBean.Issue> {
        return repository.requestFollowList()
    }

    override fun loadMoreData(url:String): Observable<HomeBean.Issue> {
        return repository.loadMoreData(url)
    }
}