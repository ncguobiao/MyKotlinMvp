package com.example.home.injection.module

import com.example.home.mvp.service.HomeService
import com.example.home.mvp.service.impl.HomeServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/1.
 */

@Module
class HomeModule {

    @Provides
    fun provideService(service: HomeServiceImpl): HomeService {
        return service
    }

}