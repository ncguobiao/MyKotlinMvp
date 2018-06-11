package com.example.discovery.mvp.constract

import com.example.baselibrary.base.IBaseView
import com.example.baselibrary.base.IPresenter
import com.example.discovery.data.bean.CategoryBean

/**
 * Created by Alienware on 2018/6/7.
 * 分类 契约类
 */
interface CategoryContract {

    interface View : IBaseView {
        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{
        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }
}