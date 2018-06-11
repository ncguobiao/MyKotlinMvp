package com.example.discovery.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.discovery.mvp.constract.CategoryDetailContract
import com.example.discovery.mvp.service.CategoryDetailService
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 * 分类详情 Presenter
 */
class CategoryDetailPresenter @Inject constructor() : BasePresenter<CategoryDetailContract.View>(), CategoryDetailContract.Presenter {
    @Inject
    lateinit var service: CategoryDetailService

    private lateinit var nextPageUrl: String

    /**
     *  获取分类详情的列表信息
     */
    override fun getCategoryDetailList(id: Long) {
        checkNetWork()
        checkViewAttached()
        mRootView?.showLoading()
        addSubscription(disposable = service.getCategoryDetailList(id)
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ issue ->
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setCateDetailList(issue.itemList)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(throwable.toString())
                    }
                })
        )

    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        addSubscription(disposable = nextPageUrl?.let {
            service.loadMoreData(it)
                    .compose(lifecycleProvider.bindToLifecycle())
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setCateDetailList(issue.itemList)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            showError(throwable.toString())
                        }
                    })

        })
    }


}