package com.example.discovery.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseMvpActivity
import com.example.baselibrary.glide.GlideApp
import com.example.baselibrary.onClick
import com.example.baselibrary.recycleView.EndlessRecyclerOnScrollListener
import com.example.baselibrary.utils.StatusBarUtil
import com.example.discovery.R
import com.example.discovery.data.bean.CategoryBean
import com.example.discovery.mvp.constract.CategoryDetailContract
import com.example.discovery.mvp.presenter.CategoryDetailPresenter
import com.example.discovery.ui.adapter.CategoryDetailAdapter
import com.example.discovery.injection.component.DaggerDiscoveryComponent
import com.example.discovery.injection.module.CategoryDetailModule
import com.example.provider.common.Constants
import com.example.provider.data.modle.bean.HomeBean
import kotlinx.android.synthetic.main.activity_category_detail.*
import org.jetbrains.anko.toast

/**
 * Created by Alienware on 2018/6/8.
 */
class CategoryDetailActivity :BaseMvpActivity<CategoryDetailPresenter>(),CategoryDetailContract.View{
    private  var categoryData: CategoryBean?=null
    private var itemList = ArrayList<HomeBean.Issue.Item>()

    override fun onError(error: String) {
        showError(error)
    }

    private val mAdapter by lazy {
        CategoryDetailAdapter(this, itemList, R.layout.item_category_detail)
    }

    override fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>) {
        this.itemList=itemList
        mAdapter.addData(itemList)
    }

    override fun showError(errorMsg: String) {
        multipleStatusView.showError()
    }

    override fun initComponent() {
        DaggerDiscoveryComponent.builder()
                .activityComponent(activityComponent)
                .categoryDetailModule(CategoryDetailModule())
                .build()
                .inject(this)

        mPresenter.attachView(this)
    }

    override fun layoutId(): Int = R.layout.activity_category_detail



    override fun initData() {
        categoryData = intent.getSerializableExtra(Constants.BUNDLE_CATEGORY_DATA) as CategoryBean
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        //加载headerImage
        GlideApp.with(this)
                .load(categoryData?.headerImage)
                .placeholder(R.color.color_darker_gray)
                .into(imageView)

        tv_category_desc.text = "#${categoryData?.description}#"

        collapsing_toolbar_layout.title = categoryData?.name
        //设置还没收缩时状态下字体颜色
        collapsing_toolbar_layout.setExpandedTitleColor(Color.WHITE)
        //设置收缩后Toolbar上字体的颜色
        collapsing_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter =mAdapter
        //实现自动加载
        mRecyclerView.addOnScrollListener(object :EndlessRecyclerOnScrollListener(){
            override fun onLoadMore() {
                mPresenter.loadMoreData()
            }

        })

        fab.onClick { toast("分享") }

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
    }

    override fun start() {
        //获取当前分类列表
        mPresenter.getCategoryDetailList(categoryData?.id!!)
    }
}