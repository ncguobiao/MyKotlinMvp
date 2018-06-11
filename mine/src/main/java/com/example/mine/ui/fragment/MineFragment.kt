package com.example.home.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.onClick
import com.example.baselibrary.showToast
import com.example.baselibrary.utils.StatusBarUtil
import com.example.mine.R
import com.example.mine.ui.activity.AboutActivity
import com.example.mine.ui.activity.WatchHistoryActivity
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by Alienware on 2018/5/31.
 * 首页精选
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    private var mTitle:String? = null

    companion object {
        fun getInstance(title:String):MineFragment{
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mine,null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

     private fun initView(){
         //状态栏透明和间距处理
         StatusBarUtil.darkMode(activity)
         StatusBarUtil.setPaddingSmart(activity, toolbar)

         tv_view_homepage.onClick(this)
         iv_avatar.onClick(this)
         iv_about.onClick(this)

         tv_collection.onClick(this)
         tv_comment.onClick(this)

         tv_mine_message.onClick(this)
         tv_mine_attention.onClick(this)
         tv_mine_cache.onClick(this)
         tv_watch_history.onClick(this)
         tv_feedback.onClick(this)
    }


    override fun onClick(v: View?) {
        when{
            v?.id==R.id.iv_avatar|| v?.id==R.id.tv_view_homepage -> {
                showToast("查看个人主页")
//                startActivity<ProfileHomePageActivity>()
            }
            v?.id==R.id.iv_about ->{
                val intent = Intent(activity, AboutActivity::class.java)
                startActivity(intent)
            }
            v?.id==R.id.tv_collection -> showToast("收藏")
            v?.id==R.id.tv_comment -> showToast("评论")
            v?.id==R.id.tv_mine_message -> showToast("我的消息")
            v?.id==R.id.tv_mine_attention -> showToast("我的关注")
            v?.id==R.id.tv_mine_attention -> showToast("我的缓存")
            v?.id==R.id.tv_watch_history -> startActivity(Intent(activity, WatchHistoryActivity::class.java))
            v?.id==R.id.tv_feedback -> showToast("意见反馈")
        }
    }


}