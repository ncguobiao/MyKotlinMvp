package com.example.home.mvp.service

import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/6/1.
 */
interface VideoDetailService {


    fun requestRelatedVideo(id: Long) : Observable<HomeBean.Issue>
}