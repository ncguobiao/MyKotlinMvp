package com.example.mine.ui.activity

import android.os.Bundle
import com.example.baselibrary.base.BaseActivity
import com.example.home.ui.fragment.MineFragment
import com.example.mine.R

/**
 * Created by Alienware on 2018/6/11.
 */
class MineActivity :BaseActivity(){

    private val mMineFragment by lazy {
        MineFragment.getInstance("我的")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)
        initView()
    }

    private fun initView() {

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.main_fragment,mMineFragment)
        beginTransaction.commit()
    }


}