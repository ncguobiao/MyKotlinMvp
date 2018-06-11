package com.example.home.mvp.service

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/1.
 */
interface HomeService {
    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int): Observable<HomeBean>

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>
}