package com.example.alienware.mykotlinmvp.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.example.alienware.mykotlinmvp.R
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.showToast
import com.example.discovery.ui.fragment.DiscoveryFragment
import com.example.home.mvp.model.bean.TabEntity
import com.example.home.ui.fragment.HomeFragment
import com.example.home.ui.fragment.MineFragment
import com.example.hot.ui.fragment.HotFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    //首页Fragment
    private val mHomeFragment by lazy { HomeFragment.getInstance("每日精选") }
    private val mDiscoveryFragment by lazy { DiscoveryFragment.getInstance("发现") }
    private val mHotFragment by lazy { HotFragment.getInstance("热门") }
    private val mMineFragment by lazy { MineFragment.getInstance("我的") }
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")
    private var mExitTime: Long = 0
    private val mTabEntities = ArrayList<CustomTabEntity>()
    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    //默认为0
    private var mIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            mIndex = it.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    /**
     * 初始化Fragment栈管理
     */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.fl_container,mHomeFragment)
        manager.add(R.id.fl_container,mDiscoveryFragment)
        manager.add(R.id.fl_container,mHotFragment)
        manager.add(R.id.fl_container,mMineFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mDiscoveryFragment)
        mStack.add(mHotFragment)
        mStack.add(mMineFragment)
    }

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
            }

        })
    }

    /**
     *切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        hideFragments(manager)
        manager.show(mStack[position])
        mIndex = position
        tab_layout.currentTab = mIndex
        manager.commit()
    }


    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(manager: FragmentTransaction) {
        for (fragment in mStack){
            manager.hide(fragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        showToast("onSaveInstanceState->"+mIndex)
        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout!=null){
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis().minus(mExitTime)<=2000){
                finish()
            }else{
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
