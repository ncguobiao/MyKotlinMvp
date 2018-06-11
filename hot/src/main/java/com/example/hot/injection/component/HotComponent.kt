package com.example.discovery.injection.component

import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.scope.PerComponentScope
import com.example.discovery.injection.module.HotModule
import com.example.hot.injection.module.RankModule
import com.example.hot.ui.fragment.HotFragment
import com.example.hot.ui.fragment.RankFragment
import dagger.Component

/**
 * Created by Alienware on 2018/6/7.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
        , modules = arrayOf(HotModule::class,
        RankModule::class
))
interface HotComponent {
    fun inject(fragment: HotFragment)

    fun inject(fragment: RankFragment)

}