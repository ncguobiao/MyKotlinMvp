package com.example.discovery.mvp.constract

import com.example.baselibrary.base.IBaseView
import com.example.baselibrary.base.IPresenter
import com.example.hot.data.bean.TabInfoBean

/**
 * Created by Alienware on 2018/6/7.
 */
interface HotTabContract {

    interface View : IBaseView {
        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode:Int)
    }


    interface Presenter:IPresenter<View>{
        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }
}