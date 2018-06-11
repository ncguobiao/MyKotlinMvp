package com.example.home.injection.component

import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.scope.PerComponentScope
import com.example.home.injection.module.HomeModule
import com.example.home.injection.module.VideoDetailModule
import com.example.home.ui.activity.VideoDetailActivity
import com.example.home.ui.fragment.HomeFragment
import dagger.Component

/**
 * Created by Alienware on 2018/6/1.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
    ,modules = arrayOf(HomeModule::class
        ,VideoDetailModule::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
    fun inject(activity: VideoDetailActivity)
}