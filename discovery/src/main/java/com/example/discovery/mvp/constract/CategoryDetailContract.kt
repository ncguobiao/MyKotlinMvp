package com.example.discovery.mvp.constract

import com.example.baselibrary.base.IBaseView
import com.example.baselibrary.base.IPresenter
import com.example.provider.data.modle.bean.HomeBean

/**
 * Created by Alienware on 2018/6/7.
 * 分类详情 契约类
 */
interface CategoryDetailContract {

    interface View:IBaseView{
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)




    }

    interface Presenter:IPresenter<View>{

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}