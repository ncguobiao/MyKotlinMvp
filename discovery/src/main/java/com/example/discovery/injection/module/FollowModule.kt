package com.example.discovery.injection.module

import com.example.discovery.mvp.service.FollowService
import com.example.discovery.mvp.service.impl.FollowServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/7.
 */
@Module
class FollowModule {
    @Provides
    fun provideService(service: FollowServiceImpl): FollowService {
        return service
    }

}