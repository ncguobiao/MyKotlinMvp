package com.example.home.mvp.service.impl

import com.example.home.data.respository.HomeRespository
import com.example.home.mvp.service.HomeService
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/1.
 */
class HomeServiceImpl @Inject constructor(): HomeService {
    @Inject
    lateinit var homeRepository: HomeRespository

    override fun requestHomeData(num: Int): Observable<HomeBean> {
        return homeRepository.requestHomeData(num)
    }

    override fun loadMoreData(url: String): Observable<HomeBean> {
        return homeRepository.loadMoreData(url)
    }
}