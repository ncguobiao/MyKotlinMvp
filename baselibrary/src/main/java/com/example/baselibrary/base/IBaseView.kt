package com.example.baselibrary.base

/**
 * Created by Alienware on 2018/5/31.
 */
interface IBaseView {

    fun showLoading()

    fun dismissLoading()

    fun onError(error:String)
}