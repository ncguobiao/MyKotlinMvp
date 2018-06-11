package com.example.discovery.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.discovery.mvp.constract.CategoryContract
import com.example.discovery.mvp.service.CategoryService
import javax.inject.Inject

/**
 * Created by Alienware on 2018/6/7.
 * 分类的 Presenter
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    @Inject
    lateinit var service: CategoryService

    /**
     * 获取分类
     */
    override fun getCategoryData() {
        checkNetWork()
        checkViewAttached()
        mRootView?.showLoading()
        addSubscription(disposable = service.getCategoryData()
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ categoryList ->
                    mRootView?.apply {
                        dismissLoading()
                        showCategory(categoryList)
                    }
                }, { t ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                }))
    }

}