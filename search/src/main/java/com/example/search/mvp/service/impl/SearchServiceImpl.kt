package com.example.home.mvp.service.impl

import com.example.home.mvp.service.SeatchService
import com.example.provider.data.modle.bean.HomeBean
import com.example.search.data.respository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/1.
 */
class SearchServiceImpl @Inject constructor() : SeatchService {
    @Inject
    lateinit var repository: SearchRepository


    override fun requestHotWordData(): Observable<ArrayList<String>> {
        return repository.requestHotWordData()
    }

    override fun querySearchData(word: String): Observable<HomeBean.Issue> {
        return repository.querySearchData(word)
    }

    override fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return repository.loadMoreData(url)
    }


}