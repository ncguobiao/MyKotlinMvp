package com.example.baselibrary.rx.scheduler

/**
 * Created by Alienware on 2018/6/1.
 */
object SchedulerUtils {

    fun <T> inToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}