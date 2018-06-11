package com.example.search.api

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Alienware on 2018/6/2.
 */
interface ApiSearchService {


    /**
     * 获取搜索信息
     */
    @GET("v1/search?&num=10&start=10")
    fun getSearchData(@Query("query") query :String) : Observable<HomeBean.Issue>

    /**
     * 热门搜索词
     */
    @GET("v3/queries/hot")
    fun getHotWord(): Observable<ArrayList<String>>

    /*
    * 获取更多的 Issue
    */
    @GET
    fun getIssueData(@Url url: String): Observable<HomeBean.Issue>

}