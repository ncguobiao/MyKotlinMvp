package com.example.home.mvp.presenter

import android.app.Activity
import com.example.baselibrary.base.BasePresenter
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.data.net.execption.ExceptionHandle
import com.example.baselibrary.dataFormat
import com.example.baselibrary.showToast
import com.example.baselibrary.utils.DisplayManager
import com.example.baselibrary.utils.NetworkUtil
import com.example.home.mvp.contract.VideoDetailContract
import com.example.home.mvp.service.VideoDetailService
import com.example.provider.data.modle.bean.HomeBean
import javax.inject.Inject

/**
 *首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */
class VideoDetailPresenter @Inject constructor() : BasePresenter<VideoDetailContract.View>(), VideoDetailContract.Presenter {


    @Inject
    lateinit var service: VideoDetailService
    /**
     * 加载视频相关的数据
     */
    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {

        val playInfo = itemInfo.data?.playInfo

        val netType = NetworkUtil.isWifi(BaseApplication.mContext)
        // 检测是否绑定 View
        checkViewAttached()
        if (playInfo!!.size > 1) {
            // 当前网络是 Wifi环境下选择高清的视频
            if (netType) {
                for (i in playInfo) {
                    if (i.type == "high") {
                        val playUrl = i.url
                        mRootView?.setVideo(playUrl)
                        break
                    }
                }
            } else {
                //否则就选标清的视频
                for (i in playInfo) {
                    if (i.type == "normal") {
                        val playUrl = i.url
                        mRootView?.setVideo(playUrl)
                        //Todo 待完善
                        (mRootView as Activity).showToast("本次消耗${(mRootView as Activity)
                                .dataFormat(i.urlList[0].size)}流量")
                        break
                    }
                }
            }
        } else {
            mRootView?.setVideo(itemInfo.data!!.playUrl)
        }
        //设置背景
        val backgroundUrl = itemInfo.data!!.cover.blurred + "/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
        backgroundUrl.let { mRootView?.setBackground(it) }

        mRootView?.setVideoInfo(itemInfo)
    }

    /**
     * 请求相关的视频数据
     */
    override fun requestRelatedVideo(id: Long) {
        checkNetWork()
        mRootView?.showLoading()
        val disposable =service.requestRelatedVideo(id)
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRecentRelatedVideo(issue.itemList)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        setErrorMsg(ExceptionHandle.handleException(t))
                    }
                })
        disposable?.let {
            addSubscription(it)
        }

    }
}