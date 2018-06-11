package com.example.home.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.home.mvp.contract.SearchContract
import com.example.home.mvp.service.SeatchService
import javax.inject.Inject

/**
 *首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */
class SearchPresenter @Inject constructor() : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    @Inject
    lateinit var service: SeatchService

    private var nextPageUrl: String? = null
    /**
     * 获取热门关键词
     */
    override fun requestHotWordData() {
        checkViewAttached()
        checkNetWork()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = service.requestHotWordData().subscribe({ string ->
            mRootView?.apply {
                setHotWordData(string)
            }
        }, { throwable ->
            mRootView?.apply {
                //处理异常
                showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
            }
        })
        )
    }


    /**
     * 查询关键词
     */
    override fun querySearchData(word: String) {
        checkViewAttached()
        checkNetWork()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = service.querySearchData(word)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        if (issue.count > 0 && issue.itemList.size > 0) {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        } else
                            setEmptyView()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        checkViewAttached()
        checkNetWork()
        nextPageUrl?.let {
            addSubscription(disposable = service.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    }))
        }
    }


}