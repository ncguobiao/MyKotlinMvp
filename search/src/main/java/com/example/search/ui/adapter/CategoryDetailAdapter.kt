package com.example.search.ui.adapter

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.baselibrary.adapter.CommonAdapter
import com.example.baselibrary.adapter.viewHolder.ViewHolder
import com.example.baselibrary.common.BaseApplication.Companion.mContext
import com.example.baselibrary.durationFormat
import com.example.baselibrary.glide.GlideApp
import com.example.provider.common.Constants
import com.example.provider.data.modle.bean.HomeBean
import com.example.provider.router.RouterPath
import com.example.search.R

/**
 * Created by Alienware on 2018/6/4.
 * 分类详情Adapter
 */
class CategoryDetailAdapter(context: Context,dataList:ArrayList<HomeBean.Issue.Item>,mLayoutId:Int):
        CommonAdapter<HomeBean.Issue.Item>(context,dataList,mLayoutId){
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        setVideoItem(holder, data)
    }

    /**
     * 加载 content item
     */
    private fun setVideoItem(holder: ViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data
        val cover = itemData?.cover?.feed?:""

        // 加载封页图
        GlideApp.with(mContext)
                .load(cover)
                .apply(RequestOptions().placeholder(R.drawable.placeholder_banner))
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_image))
        holder.setText(R.id.tv_title, itemData?.title?:"")

        // 格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        holder.setText(R.id.tv_tag, "#${itemData?.category}/$timeFormat")

        holder.setOnItemClickListener(listener = View.OnClickListener {
            goToVideoPlayer(context as Activity, holder.getView(R.id.iv_image), item)
        })
    }

    fun addData(dataList: ArrayList<HomeBean.Issue.Item>){
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, Constants.Companion.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair)
            ARouter.getInstance().build(RouterPath.Video.PATH_VIDEO)
                    .withObject(Constants.BUNDLE_VIDEO_DATA, itemData)
                    .withBoolean(Constants.Companion.TRANSITION, true)//sdk大于等于16的动画方法:
                    .withOptionsCompat(activityOptions)
                    .navigation(activity)
        } else {
            ARouter.getInstance().build(RouterPath.Video.PATH_VIDEO)
                    .withObject(Constants.BUNDLE_VIDEO_DATA, itemData)
                    .withBoolean(Constants.Companion.TRANSITION, true)
                    //参数1为打开的Activity的进入动画，参数2为当前的Activity的退出动画
                    .withTransition(R.anim.anim_in, R.anim.anim_out)
                    .navigation()
        }
    }
}