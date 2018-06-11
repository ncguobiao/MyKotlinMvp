package com.example.hot.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.baselibrary.base.BaseFragmentAdapter
import com.example.baselibrary.base.BaseMvpFragment
import com.example.baselibrary.data.net.execption.ErrorStatus
import com.example.baselibrary.showToast
import com.example.baselibrary.utils.StatusBarUtil
import com.example.discovery.injection.component.DaggerHotComponent
import com.example.discovery.injection.module.HotModule
import com.example.discovery.mvp.constract.HotTabContract
import com.example.hot.R
import com.example.hot.data.bean.TabInfoBean
import com.example.hot.mvp.presenter.HotTabPresenter
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Created by Alienware on 2018/6/8.
 */
class HotFragment : BaseMvpFragment<HotTabPresenter>(), HotTabContract.View {
    /**
     * 存放 tab 标题
     */
    private val mTabTitleList= ArrayList<String>()
    /**
     * 存放Fragment
     */
    private val mFragmentList = ArrayList<Fragment>()
    private var mTitle: String? = null



    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
    }

    override fun onError(error: String) {
        showError(error, ErrorStatus.NETWORK_ERROR)
    }



    /**
     * 设置 TabInfo
     */
    override fun setTabInfo(tabInfoBean: TabInfoBean) {
        multipleStatusView.showContent()
        //标题存入集合
        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList){it.name}
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList){RankFragment.getInstance(it.apiUrl)}


        mViewPager.adapter= BaseFragmentAdapter(childFragmentManager,mFragmentList,mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun injectComponent() {
        DaggerHotComponent.builder()
                .activityComponent(mActivityComponent)
                .hotModule(HotModule())
                .build()
                .inject(this)

        mPresenter.attachView(this)

    }

    override fun lazyLoad() {
        mPresenter.getTabInfo()
    }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(activity)
        StatusBarUtil.setPaddingSmart(activity, toolbar)
    }
}