package com.example.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.example.baselibrary.injection.module.ActivityMoudle
import com.example.baselibrary.injection.module.LifecycleProviderModule
import com.example.baselibrary.injection.scope.ActivityScope
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

/**
 * Created by Alienware on 2018/5/31.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityMoudle::class,
                LifecycleProviderModule::class))
interface ActivityComponent {

    fun activity(): Activity

    fun context(): Context

    fun lifecycleProvider():LifecycleProvider<*>
}