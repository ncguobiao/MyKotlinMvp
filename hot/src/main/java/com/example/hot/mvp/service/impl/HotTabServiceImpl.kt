package com.example.discovery.mvp.service.impl

import com.example.discovery.data.repository.HotTabRepository
import com.example.discovery.mvp.service.HotTabService
import com.example.hot.data.bean.TabInfoBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class HotTabServiceImpl @Inject constructor(): HotTabService {

    @Inject
    lateinit var repository: HotTabRepository
    override fun getTabInfo(): Observable<TabInfoBean> {
        return repository.getTabInfo()
    }

}