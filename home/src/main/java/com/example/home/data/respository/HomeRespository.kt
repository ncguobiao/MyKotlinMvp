package com.example.home.data.respository

import com.example.baselibrary.compose
import com.example.baselibrary.data.net.RetrofitFactory
import com.example.home.api.ApiHomeService
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/1.
 */
class HomeRespository @Inject constructor() {

    /**
     * 获取首页Banner数据
     */
    fun requestHomeData(num: Int): Observable<HomeBean> {
        return RetrofitFactory.instance.create(ApiHomeService::class.java).getFirstHomeData(num).compose()
    }

    /**
     * 加载更多
     */
     fun loadMoreData(url: String): Observable<HomeBean> {
        return RetrofitFactory.instance.create(ApiHomeService::class.java).getMoreHomeData(url).compose()
    }
}