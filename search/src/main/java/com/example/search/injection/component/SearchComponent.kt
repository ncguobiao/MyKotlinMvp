package com.example.search.injection.component

import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.scope.PerComponentScope
import com.example.search.ui.activity.SearchActivity
import com.example.search.injection.module.SearchModule
import dagger.Component

/**
 * Created by Alienware on 2018/6/4.
 */

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
        , modules = arrayOf(SearchModule::class))
interface SearchComponent {
    fun inject(activity: SearchActivity)
}