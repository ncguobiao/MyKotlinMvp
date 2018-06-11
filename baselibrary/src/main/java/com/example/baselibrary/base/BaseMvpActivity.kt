package com.example.baselibrary.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import com.classic.common.MultipleStatusView
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.component.DaggerActivityComponent
import com.example.baselibrary.injection.module.ActivityMoudle
import com.example.baselibrary.injection.module.LifecycleProviderModule
import javax.inject.Inject

/**
 * BaseActivity基类
 */
abstract class BaseMvpActivity<T:BasePresenter<*>> : BaseActivity() ,IBaseView{
    /**
     * 多种状态的 View 的切换
     */
    //Presenter泛型，Dagger注入
    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initActivityInjection()
        initComponent()
        initData()
        initView()
        start()
        initListener()
    }

    abstract fun initComponent()

    private fun initActivityInjection() {
         activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                 .lifecycleProviderModule(LifecycleProviderModule(this))
                .activityMoudle(ActivityMoudle(this))
                .build()
    }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()


    private fun initListener(){
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open  val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
        BaseApplication.getRefWatcher(this)?.watch(this)

    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }



}