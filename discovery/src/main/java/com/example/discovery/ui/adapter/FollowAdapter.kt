package com.example.discovery.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.adapter.CommonAdapter
import com.example.baselibrary.adapter.viewHolder.MultipleType
import com.example.baselibrary.adapter.viewHolder.ViewHolder
import com.example.baselibrary.glide.GlideApp
import com.example.discovery.R
import com.example.provider.data.modle.bean.HomeBean

/**
 * Created by Alienware on 2018/6/7.
 */
class FollowAdapter(context: Context,dataList:ArrayList<HomeBean.Issue.Item>)
    :CommonAdapter<HomeBean.Issue.Item>(context,dataList,object :MultipleType<HomeBean.Issue.Item>{
    override fun getLayoutId(item: HomeBean.Issue.Item, position: Int): Int {
        return when{
            item.type=="videoCollectionWithBrief"->
                    R.layout.item_follow
            else-> throw IllegalAccessException("Api 解析出错了，出现其他类型")


        }
    }

}){
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        when{
            data.type=="videoCollectionWithBrief"->setAuthorInfo(data,holder)
        }
    }

    /**
     * 加载作者信息
     */
    private fun setAuthorInfo(item: HomeBean.Issue.Item, holder: ViewHolder) {
        val headerData = item.data?.header
        /**
         * 加载作者头像
         */
        holder.setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(headerData?.icon!!) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(context)
                        .load(path)
                        .placeholder(R.mipmap.default_avatar).circleCrop()
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(holder.getView(R.id.iv_avatar))
            }

        })
        holder.setText(R.id.tv_title, headerData.title)
        holder.setText(R.id.tv_desc, headerData.description)

        val recyclerView = holder.getView<RecyclerView>(R.id.fl_recyclerView)
        /**
         * 设置嵌套水平的 RecyclerView
         */
        recyclerView.layoutManager = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = FollowHorizontalAdapter(context, item.data!!.itemList,R.layout.item_follow_horizontal)
    }

    fun addData(dataList: ArrayList<HomeBean.Issue.Item>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

}