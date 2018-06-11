package com.example.hot.injection.module

import com.example.hot.mvp.service.RankService
import com.example.hot.mvp.service.impl.RankServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alienware on 2018/6/7.
 */
@Module
class RankModule {
    @Provides
    fun provideService(service: RankServiceImpl): RankService {
        return service
    }

}