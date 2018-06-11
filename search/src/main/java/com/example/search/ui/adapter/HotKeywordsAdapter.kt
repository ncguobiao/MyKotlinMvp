package com.example.search.ui.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.baselibrary.adapter.CommonAdapter
import com.example.baselibrary.adapter.viewHolder.ViewHolder
import com.example.search.R
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * Created by xuhao on 2017/12/4.
 * desc: Tag 标签布局的 Adapter
 */
class HotKeywordsAdapter(mContext: Context,mList: ArrayList<String>, layoutId: Int) :
        CommonAdapter<String>(mContext, mList, layoutId){




    /**
     * Kotlin的函数可以作为参数，写callback的时候，可以不用interface了
     */

    private var mOnTagItemClick: ((tag:String) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(tag:String) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {

        holder.setText(R.id.tv_title,data)

        val params = holder.getView<TextView>(R.id.tv_title).layoutParams
        if(params is FlexboxLayoutManager.LayoutParams){
            params.flexGrow = 1.0f
        }

        holder.setOnItemClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                mOnTagItemClick?.invoke(data)
            }

        }

        )

    }


}