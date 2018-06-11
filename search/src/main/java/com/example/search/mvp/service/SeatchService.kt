package com.example.home.mvp.service

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/1.
 */
interface SeatchService {

     fun requestHotWordData() :Observable<ArrayList<String>>

     fun querySearchData(word: String) :Observable<HomeBean.Issue>

     fun loadMoreData(url :String) :Observable<HomeBean.Issue>

}