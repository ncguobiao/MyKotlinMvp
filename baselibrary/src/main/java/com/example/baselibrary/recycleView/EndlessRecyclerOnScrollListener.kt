package com.example.baselibrary.recycleView

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Alienware on 2018/3/2.
 * 微信：
 * QQ号：568164681
 * 作用：xxxx
 */
abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {
    //用来标记是否正在向上滑动
    private var isSlidingUpward = false

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val manager = recyclerView!!.layoutManager as LinearLayoutManager
        // 当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的itemPosition
            val lastItemPosition = manager.findLastCompletelyVisibleItemPosition()
            val itemCount = manager.itemCount

            // 判断是否滑动到了最后一个item，并且是向上滑动
            if (lastItemPosition ==(itemCount - 1) && isSlidingUpward) {
                //加载更多
                onLoadMore()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSlidingUpward = dy > 0
    }

    /**
     * 加载更多回调
     */
    abstract fun onLoadMore()
}