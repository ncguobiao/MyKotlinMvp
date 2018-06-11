package com.example.discovery.injection.component

import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.scope.PerComponentScope
import com.example.discovery.ui.CategoryDetailActivity
import com.example.discovery.ui.fragment.CategoryFragment
import com.example.discovery.ui.fragment.FollowFragment
import com.example.discovery.injection.module.CategoryDetailModule
import com.example.discovery.injection.module.CategoryModule
import com.example.discovery.injection.module.FollowModule
import dagger.Component

/**
 * Created by Alienware on 2018/6/7.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
        , modules = arrayOf(FollowModule::class,
        CategoryModule::class,
        CategoryDetailModule::class))
interface DiscoveryComponent {
    fun inject(fragment: FollowFragment)

    fun inject(fragment: CategoryFragment)

    fun inject(activity: CategoryDetailActivity)
}