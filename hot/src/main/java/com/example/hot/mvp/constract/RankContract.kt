package com.example.discovery.mvp.constract

import com.example.baselibrary.base.IBaseView
import com.example.baselibrary.base.IPresenter
import com.example.provider.data.modle.bean.HomeBean

/**
 * Created by Alienware on 2018/6/7.
 *  契约类
 */
interface RankContract {

    interface View:IBaseView{
        /**
         * 设置排行榜的数据
         */
        fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }


    interface Presenter:IPresenter<View>{
        /**
         * 获取 TabInfo
         */
        fun requestRankList(apiUrl:String)
    }
}