package com.example.discovery.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.discovery.mvp.constract.RankContract
import com.example.hot.mvp.service.RankService
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 */
class RankPresenter @Inject constructor() : BasePresenter<RankContract.View>(), RankContract.Presenter {


    @Inject
    lateinit var service: RankService


    override fun requestRankList(apiUrl: String) {
        checkNetWork()
        checkViewAttached()
        mRootView?.showLoading()
        addSubscription(disposable = apiUrl?.let {
            service.requestRankList(it)
                    .compose(lifecycleProvider.bindToLifecycle())
                    .subscribe({
                        issue->
                        mRootView?.apply {
                            dismissLoading()
                            setRankList(issue.itemList)
                        }

                    },{t ->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                        }
                    })
        })
    }
}