package com.example.discovery.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.base.BaseFragmentAdapter
import com.example.baselibrary.utils.StatusBarUtil
import com.example.baselibrary.utils.TabLayoutHelper
import com.example.discovery.R
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Created by Alienware on 2018/6/6.
 * 发现(和热门首页同样的布局）
 */
class DiscoveryFragment :BaseFragment() {
    private val tabList=ArrayList<String>()

    private val fragments = ArrayList<Fragment>()

    private var mTitle:String?= null
    companion object {
        fun getInstance(title:String):DiscoveryFragment{
            val fragment = DiscoveryFragment()
            val bundle =Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(activity)
        StatusBarUtil.setPaddingSmart(activity, toolbar)

        tv_header_title.text = mTitle

        tabList.add("关注")
        tabList.add("分类")

        fragments.add(FollowFragment.getInstance("111"))
        fragments.add(CategoryFragment.getInstance("112"))

        /**
         * getSupportFragmentManager() 替换为getChildFragmentManager()
         */
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,fragments,tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
    }

}