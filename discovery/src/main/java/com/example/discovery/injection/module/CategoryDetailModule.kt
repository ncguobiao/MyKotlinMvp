package com.example.discovery.injection.module

import com.example.discovery.mvp.service.CategoryDetailService
import com.example.discovery.mvp.service.impl.CategoryDetailServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/7.
 */
@Module
class CategoryDetailModule {

    @Provides
    fun provideService(service: CategoryDetailServiceImpl): CategoryDetailService {
        return service
    }
}