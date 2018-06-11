package com.example.search.data.respository

import com.example.baselibrary.compose
import com.example.baselibrary.data.net.RetrofitFactory
import com.example.provider.data.modle.bean.HomeBean
import com.example.search.api.ApiSearchService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/4.
 */
class SearchRepository @Inject constructor() {
    /**
     * 搜索关键词返回的结果
     */
    fun requestHotWordData(): Observable<ArrayList<String>> {
        return RetrofitFactory.instance.create(ApiSearchService::class.java).getHotWord().compose()
    }
    /**
     * 搜索关键词返回的结果
     */
    fun querySearchData(word: String): Observable<HomeBean.Issue> {
        return RetrofitFactory.instance.create(ApiSearchService::class.java).getSearchData(word).compose()
    }
    /**
     * 加载更多数据
     */
    fun loadMoreData(url:String): Observable<HomeBean.Issue> {
        return RetrofitFactory.instance.create(ApiSearchService::class.java).getIssueData(url).compose()
    }

}