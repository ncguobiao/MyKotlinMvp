package com.example.discovery.injection.module

import com.example.discovery.mvp.service.CategoryService
import com.example.discovery.mvp.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/7.
 */
@Module
class CategoryModule {

    @Provides
    fun provideService(service: CategoryServiceImpl): CategoryService {
        return service
    }
}