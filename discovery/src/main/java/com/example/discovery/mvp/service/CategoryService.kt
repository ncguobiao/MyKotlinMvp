package com.example.discovery.mvp.service

import com.example.discovery.data.bean.CategoryBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/7.
 */
interface CategoryService {
    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>>

}