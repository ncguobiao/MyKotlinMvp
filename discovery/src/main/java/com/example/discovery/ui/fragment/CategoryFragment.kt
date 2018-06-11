package com.example.discovery.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.baselibrary.base.BaseMvpFragment
import com.example.baselibrary.data.net.execption.ErrorStatus
import com.example.baselibrary.showToast
import com.example.baselibrary.utils.DisplayManager
import com.example.discovery.R
import com.example.discovery.data.bean.CategoryBean
import com.example.discovery.mvp.constract.CategoryContract
import com.example.discovery.mvp.presenter.CategoryPresenter
import com.example.discovery.ui.adapter.CategoryAdapter
import com.example.discovery.injection.component.DaggerDiscoveryComponent
import com.example.discovery.injection.module.CategoryModule
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by Alienware on 2018/6/7.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryContract.View {
    private var mCategoryList = ArrayList<CategoryBean>()
    private lateinit var mTitle: String

    private val mAdapter by lazy {
        CategoryAdapter(activity, mCategoryList, R.layout.item_category)
    }

    companion object {
        fun getInstance(title: String): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }

    }

    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()

    }

    override fun onError(error: String) {
        showError(error, ErrorStatus.NETWORK_ERROR)
    }


    override fun showCategory(categoryList: ArrayList<CategoryBean>) {
        mCategoryList = categoryList
        mAdapter.setData(mCategoryList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()
        } else {
            multipleStatusView?.showError()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_category

    override fun injectComponent() {
        DaggerDiscoveryComponent.builder()
                .activityComponent(mActivityComponent)
                .categoryModule(CategoryModule())
                .build()
                .inject(this)

        mPresenter.attachView(this)
    }

    override fun lazyLoad() {
        mPresenter.getCategoryData()
    }

    override fun initView() {
        mLayoutStatusView = multipleStatusView

        mRecyclerView.layoutManager = GridLayoutManager(activity,2)
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!

                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }
        })

    }
}