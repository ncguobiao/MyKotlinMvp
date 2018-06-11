package com.example.mine.ui.adapter

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.adapter.CommonAdapter
import com.example.baselibrary.adapter.viewHolder.ViewHolder
import com.example.baselibrary.common.BaseApplication.Companion.mContext
import com.example.baselibrary.durationFormat
import com.example.baselibrary.glide.GlideApp
import com.example.mine.R
import com.example.provider.common.Constants
import com.example.provider.data.modle.bean.HomeBean
import com.example.provider.router.RouterPath

/**
 * Created by Alienware on 2018/6/11.
 */
class WatchHistoryAdapter (context: Context, dataList: ArrayList<HomeBean.Issue.Item>, layoutId: Int):

CommonAdapter<HomeBean.Issue.Item>(context,dataList,layoutId){

    //绑定数据
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        with(holder) {
            setText(R.id.tv_title, data.data?.title!!)
            setText(R.id.tv_tag, "#${data.data!!.category} / ${durationFormat(data.data!!.duration)}")
            setImagePath(R.id.iv_video_small_card, object : ViewHolder.HolderImageLoader(data.data!!.cover.detail) {
                override fun loadImage(iv: ImageView, path: String) {
                    GlideApp.with(mContext)
                            .load(path)
                            .placeholder(R.drawable.placeholder_banner)
                            .transition(DrawableTransitionOptions().crossFade())
                            .into(iv)
                }
            })
        }
        holder.getView<TextView>(R.id.tv_title).setTextColor(mContext.resources.getColor(R.color.color_black))
        holder.setOnItemClickListener(listener = View.OnClickListener {
            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_video_small_card), data)
        })
    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, Constants.IMG_TRANSITION)
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