package com.example.discovery.injection.module

import com.example.discovery.mvp.service.HotTabService
import com.example.discovery.mvp.service.impl.HotTabServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/7.
 */
@Module
class HotModule {
    @Provides
    fun provideService(service: HotTabServiceImpl): HotTabService {
        return service
    }

}