package com.example.discovery.data.repository

import com.example.baselibrary.compose
import com.example.baselibrary.data.net.RetrofitFactory
import com.example.discovery.api.DiscoveryApi
import com.example.discovery.data.bean.CategoryBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class CategoryRepository @Inject constructor() {

     fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitFactory.instance.create(DiscoveryApi::class.java).getCategory().compose()
    }
}