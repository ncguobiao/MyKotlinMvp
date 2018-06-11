package com.example.discovery.mvp.service

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/7.
 */
interface CategoryDetailService {
    /**
     * 获取分类下的 List 数据
     */
    fun getCategoryDetailList(id: Long) :Observable<HomeBean.Issue>

    /**
     * 加载更多数据
     */
    fun loadMoreData(url:String) :Observable<HomeBean.Issue>

}