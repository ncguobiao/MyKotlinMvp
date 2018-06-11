package com.example.baselibrary.injection.component

import android.content.Context
import com.example.baselibrary.injection.module.AppMoudle
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alienware on 2018/5/31.
 */
@Singleton
@Component(modules = arrayOf(AppMoudle::class))
interface AppComponent {
    fun context():Context
}