package com.example.home.mvp.contract

import com.example.baselibrary.base.IBaseView
import com.example.baselibrary.base.IPresenter
import com.example.provider.data.modle.bean.HomeBean

/**
 *  搜索契约类
 */
interface SearchContract {
    interface View:IBaseView{
        /**
         * 设置热门关键词数据
         */
        fun setHotWordData(string:ArrayList<String>)

        /**
         * 设置热门关键词返回结果
         */
        fun setSearchResult(issue: HomeBean.Issue)

        /**
         * 关闭软件盘
         */
        fun closeSoftKeyboard()

        /**
         * 设置空View
         */
        fun setEmptyView()

        fun showError(error: String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{
        /**
         * 获取热门关键字的数据
         */
        fun requestHotWordData()

        /**
         * 查询搜索
         */
        fun querySearchData(word:String)

        /**
         * 加载更多
         */
        fun loadMoreData()
    }
}