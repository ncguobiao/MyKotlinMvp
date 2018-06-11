package com.example.hot.mvp.service.impl

import com.example.discovery.data.repository.RankRepository
import com.example.hot.mvp.service.RankService
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/8.
 */
class RankServiceImpl @Inject constructor() : RankService {
    @Inject
    lateinit var repository: RankRepository

    override fun requestRankList(apiUrl: String): Observable<HomeBean.Issue> {
        return repository.requestRankList(apiUrl)
    }
}