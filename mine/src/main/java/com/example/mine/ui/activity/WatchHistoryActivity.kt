package com.example.mine.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.utils.StatusBarUtil
import com.example.baselibrary.utils.WatchHistoryUtils
import com.example.mine.R
import com.example.mine.ui.adapter.WatchHistoryAdapter
import com.example.provider.common.Constants
import com.example.provider.data.modle.bean.HomeBean
import kotlinx.android.synthetic.main.layout_watch_history.*
import java.util.*

/**
 * Created by Alienware on 2018/6/11.
 */
class WatchHistoryActivity :BaseActivity(){
    private var itemListData =ArrayList<HomeBean.Issue.Item>()

    companion object {
        private val HISTORY_MAX = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_watch_history)
        initData()
        initView()
    }

    private fun initView() {
        //返回
        toolbar.setNavigationOnClickListener { finish() }

        val mAdapter = WatchHistoryAdapter(this, itemListData, R.layout.item_video_small_card)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

        if(itemListData.size>1){
            multipleStatusView.showContent()
        }else{
            multipleStatusView.showEmpty()
        }

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
        StatusBarUtil.setPaddingSmart(this,mRecyclerView)

    }

    private fun initData() {
        multipleStatusView.showLoading()
        itemListData = queryWatchHistory()

    }

    /**
     * 查询观看的历史记录
     */
    private fun queryWatchHistory():ArrayList<HomeBean.Issue.Item> {
        val watchList = ArrayList<HomeBean.Issue.Item>()
        val hisAll = WatchHistoryUtils.getAll(Constants.FILE_WATCH_HISTORY_NAME,BaseApplication.mContext) as Map<*, *>
        //将key排序升序
        val keys = hisAll.keys.toTypedArray()
        Arrays.sort(keys)
        val keyLength = keys.size
        //这里计算 如果历史记录条数是大于 可以显示的最大条数，则用最大条数做循环条件，防止历史记录条数-最大条数为负值，数组越界
        val hisLength = if (keyLength > HISTORY_MAX) HISTORY_MAX else keyLength
        // 反序列化和遍历 添加观看的历史记录
        (1..hisLength).mapTo(watchList) {
            WatchHistoryUtils.getObject(Constants.FILE_WATCH_HISTORY_NAME,BaseApplication.mContext,
                    keys[keyLength - it] as String) as HomeBean.Issue.Item
        }

        return watchList
    }
}