package com.example.baselibrary.injection.module

import android.app.Activity
import com.example.baselibrary.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 *  Application级别Module
 */
@Module
class ActivityMoudle(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }

}