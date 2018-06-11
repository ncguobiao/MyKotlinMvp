package com.example.baselibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.baselibrary.adapter.viewHolder.MultipleType
import com.example.baselibrary.adapter.viewHolder.ViewHolder
import com.example.baselibrary.listener.OnItemClickListener
import com.example.baselibrary.listener.OnItemLongClickListener

/**
 * Created by Alienware on 2018/6/1.
 */
abstract class CommonAdapter<T>(var context: Context,var mData:ArrayList<T>,
                       private var mLayoutId:Int):RecyclerView.Adapter<ViewHolder>() {

    protected var mInflater: LayoutInflater?

    init {
        mInflater =LayoutInflater.from(context)

    }
    //需要多布局
    constructor(context: Context, data: ArrayList<T>, typeSupport: MultipleType<T>):this(context,data,-1){
        this.mTypeSupport = typeSupport
    }
    private var mTypeSupport: MultipleType<T>? = null
    private val mItemClickListener: OnItemClickListener?=null
    //使用接口回调点击事件
    private var mItemLongClickListener: OnItemLongClickListener? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //绑定数据
        bindData(holder,mData[position],position)
//        if (mItemClickListener != null) {
//            holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
//        }
//        //长按点击事件
//        if (mItemLongClickListener != null) {
//            holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
//        }
            //条目点击事件
            mItemClickListener?.let {
                holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
            }

            //长按点击事件
            mItemLongClickListener?.let {
                holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
            }
    }

    abstract fun bindData(holder: ViewHolder, data: T, position: Int)

    override fun getItemCount()=mData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mTypeSupport!= null){
            //需要多布局
            mLayoutId=viewType
        }
        //创建view
        val view = mInflater?.inflate(mLayoutId,parent,false)
        return ViewHolder(view!!)
    }

    override fun getItemViewType(position: Int): Int {
        //多布局问题
        return mTypeSupport?.getLayoutId(mData[position], position) ?: super.getItemViewType(position)
    }

}