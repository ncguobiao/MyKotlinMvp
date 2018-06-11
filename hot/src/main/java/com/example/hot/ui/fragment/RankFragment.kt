package com.example.hot.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseMvpFragment
import com.example.baselibrary.data.net.execption.ErrorStatus
import com.example.baselibrary.showToast
import com.example.discovery.injection.component.DaggerHotComponent
import com.example.discovery.mvp.constract.RankContract
import com.example.discovery.mvp.presenter.RankPresenter
import com.example.hot.R
import com.example.hot.injection.module.RankModule
import com.example.hot.ui.adapter.CategoryDetailAdapter
import com.example.provider.data.modle.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_rank.*

/**
 * Created by Alienware on 2018/6/8.
 */
class RankFragment : BaseMvpFragment<RankPresenter>(), RankContract.View {
    private var apiUrl: String? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private val mAdapter by lazy {
        CategoryDetailAdapter(activity, itemList, R.layout.item_category_detail)
    }

    companion object {
        fun getInstance(apiUrl: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.apiUrl = apiUrl
            return fragment
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
    }

    override fun onError(error: String) {
        showError(error,ErrorStatus.NETWORK_ERROR)
    }

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {
        multipleStatusView.showContent()
        mAdapter.addData(itemList)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank

    override fun injectComponent() {
        DaggerHotComponent.builder()
                .activityComponent(mActivityComponent)
                .rankModule(RankModule())
                .build()
                .inject(this)

        mPresenter.attachView(this)

    }

    override fun lazyLoad() {
        if (!apiUrl.isNullOrEmpty())
        mPresenter.requestRankList(apiUrl!!)
    }

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter

        mLayoutStatusView =multipleStatusView
    }
}