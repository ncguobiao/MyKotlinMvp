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
class VideoDetailRespository @Inject constructor() {

    /**
     * 获取首页Banner数据
     */
    fun getRelatedData(id: Long): Observable<HomeBean.Issue> {
        return RetrofitFactory.instance.create(ApiHomeService::class.java).getRelatedData(id).compose()
    }

}