package com.example.search.injection.module

import com.example.home.mvp.service.SeatchService
import com.example.home.mvp.service.impl.SearchServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/4.
 */

@Module
class SearchModule {
    @Provides
     fun provideService(service: SearchServiceImpl): SeatchService{
        return  service
    }
}