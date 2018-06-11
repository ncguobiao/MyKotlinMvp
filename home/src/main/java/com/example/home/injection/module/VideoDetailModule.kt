package com.example.home.injection.module

import com.example.home.mvp.service.VideoDetailService
import com.example.home.mvp.service.impl.VideoDetailServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/1.
 */

@Module
class VideoDetailModule {

    @Provides
    fun provideService(service: VideoDetailServiceImpl): VideoDetailService {
        return service
    }
}