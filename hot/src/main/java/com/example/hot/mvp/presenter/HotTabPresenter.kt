package com.example.hot.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.discovery.mvp.constract.HotTabContract
import com.example.discovery.mvp.service.HotTabService
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class HotTabPresenter @Inject constructor() : BasePresenter<HotTabContract.View>(), HotTabContract.Presenter {
    @Inject
    lateinit var service:HotTabService

    override fun getTabInfo() {
        checkNetWork()
        checkViewAttached()
        mRootView?.showLoading()
        addSubscription(disposable = service.getTabInfo()
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({
                    tabInfo->
                    mRootView?.setTabInfo(tabInfo)
                },{
                    throwable->
                    //处理异常
                    mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                }))

    }

}