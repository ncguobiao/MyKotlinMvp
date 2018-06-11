package com.example.baselibrary.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 *  Rx生命周期管理用Module
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider:LifecycleProvider<*>) {

    @Provides
    fun provideLifecycleProvider():LifecycleProvider<*> = this.lifecycleProvider
}