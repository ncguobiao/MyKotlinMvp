package com.example.baselibrary.base

import android.content.Context
import com.example.baselibrary.common.BaseApplication.Companion.mContext
import com.example.baselibrary.utils.NetworkUtil
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 *MVP中P层 基类
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {
    //Dagger注入，Rx生命周期管理
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
    @Inject
    lateinit var context: Context

    private var weakReference: WeakReference<T>? = null

    var mRootView: T? = null

    private var compositeDisposable = CompositeDisposable()

    override fun attachView(mRootView: T) {
        weakReference = WeakReference(mRootView)
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    open fun getView(): T? {
        mRootView = weakReference?.get()
        return mRootView
    }

    private val isViewAttached: Boolean
        get() = getView() != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class MvpViewNotAttachedException internal constructor()
        : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

    /*
        检查网络是否可用
     */
    fun checkNetWork():Boolean{
        if(NetworkUtil.isNetworkAvailable(mContext)){
            return true
        }
        mRootView?.let {
            mRootView!!.onError("网络不可用")
        }
        return false
    }
}