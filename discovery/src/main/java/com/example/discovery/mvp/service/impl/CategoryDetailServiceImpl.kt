package com.example.discovery.mvp.service.impl

import com.example.discovery.data.repository.CategoryDetailRepository
import com.example.discovery.mvp.service.CategoryDetailService
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class CategoryDetailServiceImpl @Inject constructor() : CategoryDetailService {

    @Inject
    lateinit var repository: CategoryDetailRepository

    override fun loadMoreData(url:String): Observable<HomeBean.Issue> {
      return  repository.loadMoreData(url)
    }

    override fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return  repository.getCategoryDetailList(id)
    }



}