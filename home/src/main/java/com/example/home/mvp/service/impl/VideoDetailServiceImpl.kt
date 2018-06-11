package com.example.home.mvp.service.impl

import com.example.home.data.respository.VideoDetailRespository
import com.example.home.mvp.service.VideoDetailService
import com.example.provider.data.modle.bean.HomeBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/1.
 */
class VideoDetailServiceImpl @Inject constructor() :VideoDetailService{

    @Inject
    lateinit var repository: VideoDetailRespository

    /**
     * 请求相关的视频数据
     */
    override fun requestRelatedVideo(id: Long): Observable<HomeBean.Issue> {
      return  repository.getRelatedData(id)
    }
}