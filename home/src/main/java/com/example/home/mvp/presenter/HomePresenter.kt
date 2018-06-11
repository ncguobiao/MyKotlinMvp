package com.example.home.mvp.presenter

import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.home.mvp.contract.HomeContract
import com.example.home.mvp.service.HomeService
import com.example.provider.data.modle.bean.HomeBean
import javax.inject.Inject

/**
 *首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */
class HomePresenter @Inject constructor() : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    @Inject
    lateinit var service: HomeService

    private var bannerHomeBean: HomeBean?=null

    private  var nextPageUrl: String?=null//加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    /**
     * 获取首页精选数据 banner 加 一页数据
     */
    override fun requestHomeData(num: Int) {
        //检测是否绑定 View
        checkViewAttached()
        checkNetWork()
        mRootView?.showLoading()
        val disposable = service.requestHomeData(num)
                .compose(lifecycleProvider.bindToLifecycle())
                .flatMap({ homeBean ->
            //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
            val bannerItemList = homeBean.issueList[0].itemList
            bannerItemList.filter { item ->
                item.type == "banner2" || item.type == "horizontalScrollCard"
            }.forEach { item ->
                //移除 item
                bannerItemList.remove(item)
            }
            bannerHomeBean = homeBean //记录第一页是当做 banner 数据
            //根据 nextPageUrl 请求下一页数据
            service.loadMoreData(homeBean.nextPageUrl)
        }).subscribe({ homeBean ->
            mRootView?.apply {
                dismissLoading()

                nextPageUrl = homeBean.nextPageUrl
                //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                val newBannerItemList = homeBean.issueList[0].itemList

                newBannerItemList.filter { item ->
                    item.type == "banner2" || item.type == "horizontalScrollCard"
                }.forEach { item ->
                    //移除 item
                    newBannerItemList.remove(item)
                }
                // 重新赋值 Banner 长度
                bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

                //赋值过滤后的数据 + banner 数据
                bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                setHomeData(bannerHomeBean!!)

            }
        }, { t ->
            getView()?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })
        disposable?.let {
            addSubscription(it)
        }
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        // 检测是否绑定 View
        checkViewAttached()
        checkNetWork()
        val disposable = nextPageUrl?.let {
            service.loadMoreData(it)
                    .compose(lifecycleProvider.bindToLifecycle())
                    .subscribe({ homeBean->
                        mRootView?.apply {
                            //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                            val newItemList = homeBean.issueList[0].itemList

                            newItemList.filter { item ->
                                item.type=="banner2"||item.type=="horizontalScrollCard"
                            }.forEach{ item ->
                                //移除 item
                                newItemList.remove(item)
                            }

                            nextPageUrl = homeBean.nextPageUrl
                            setMoreData(newItemList)
                        }

                    },{ t ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })


        }
        disposable?.let {
            addSubscription(it)
        }
    }

}