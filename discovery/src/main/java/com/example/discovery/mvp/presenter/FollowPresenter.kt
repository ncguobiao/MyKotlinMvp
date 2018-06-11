package com.example.discovery.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.discovery.mvp.constract.FollowContract
import com.example.discovery.mvp.service.FollowService
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class FollowPresenter @Inject constructor() : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

    @Inject
    lateinit var service: FollowService

    private lateinit var nextPageUrl: String

    /**
     * 请求关注数据
     */
    override fun requestFollowList() {
        checkNetWork()
        checkViewAttached()
        mRootView?.showLoading()
        addSubscription(disposable = service.requestFollowList()
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        addSubscription(disposable = service.loadMoreData(nextPageUrl)
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ issue ->
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }
}