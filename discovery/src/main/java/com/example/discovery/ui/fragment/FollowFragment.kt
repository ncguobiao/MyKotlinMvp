package com.example.discovery.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.baselibrary.base.BaseMvpFragment
import com.example.baselibrary.data.net.execption.ErrorStatus
import com.example.baselibrary.showToast
import com.example.discovery.R
import com.example.discovery.mvp.constract.FollowContract
import com.example.discovery.mvp.presenter.FollowPresenter
import com.example.discovery.ui.adapter.FollowAdapter
import com.example.discovery.injection.component.DaggerDiscoveryComponent
import com.example.discovery.injection.module.FollowModule
import com.example.provider.data.modle.bean.HomeBean
import kotlinx.android.synthetic.main.layout_recyclerview.*

/**
 * Created by Alienware on 2018/6/7.
 */
class FollowFragment : BaseMvpFragment<FollowPresenter>(), FollowContract.View {

    private var itemList = ArrayList<HomeBean.Issue.Item>()
    private var loadingMore = false

    private val mFollowAdapter by lazy {
        FollowAdapter(activity, itemList)
    }

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): FollowFragment {
            val fragment = FollowFragment()
            val buldle = Bundle()
            fragment.arguments = buldle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView.showContent()
    }

    override fun onError(error: String) {
       showError(error,ErrorStatus.NETWORK_ERROR)
    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
        loadingMore = false
        itemList = issue.itemList
        mFollowAdapter.addData(itemList)
    }

    /**
     * 显示错误信息
     */
    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_recyclerview

    override fun injectComponent() {
        DaggerDiscoveryComponent.builder()
                .activityComponent(mActivityComponent)
                .followModule(FollowModule())
                .build()
                .inject(this)

        mPresenter.attachView(this)
    }

    override fun lazyLoad() {
        mPresenter.requestFollowList()
    }

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFollowAdapter
        //实现自动加载
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView.layoutManager.itemCount
                val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })
        mLayoutStatusView = multipleStatusView
    }
}