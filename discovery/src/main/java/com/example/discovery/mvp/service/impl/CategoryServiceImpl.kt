package com.example.discovery.mvp.service.impl

import com.example.discovery.data.bean.CategoryBean
import com.example.discovery.data.repository.CategoryRepository
import com.example.discovery.mvp.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {
    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return repository.getCategoryData()
    }
}