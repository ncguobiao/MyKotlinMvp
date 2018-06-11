package com.example.baselibrary.adapter.viewHolder

/**
 * Created by Alienware on 2018/6/1.
 */
interface MultipleType<in T> {
    fun getLayoutId(item:T,position: Int):Int
}