package com.example.baselibrary

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by Alienware on 2018/5/31.
 */

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity.applicationContext, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}


fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(BaseApplication.mContext, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun <T> Observable<T>.compose(): Observable<T> {
    return this.compose(SchedulerUtils.inToMain())

}

fun getColor(colorId: Int): Int {
    return BaseApplication.mContext.resources.getColor(colorId)
}

inline val Fragment.act: Activity
    get() = activity

fun durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    return if (minute <= 9) {
        if (second <= 9) {
            "0$minute' 0$second''"
        } else {
            "0$minute' $second''"
        }
    } else {
        if (second <= 9) {
            "$minute' 0$second''"
        } else {
            "$minute' $second''"
        }
    }
}

/**
 * 数据流量格式化
 */
fun Context.dataFormat(total: Long): String {
    var result: String
    var speedReal: Int = (total / (1024)).toInt()
    result = if (speedReal < 512) {
        speedReal.toString() + " KB"
    } else {
        val mSpeed = speedReal / 1024.0
        (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
    }
    return result
}

/**
 * 扩展点击事件，参数为方法
 */
fun View.onClick(method:()->Unit):View{
    setOnClickListener { method()}
    return this
}

/**
 * 扩展点击事件
 */
fun View.onClick(listener:View.OnClickListener):View{
    setOnClickListener(listener)
    return this
}

/**
 * 初始化View
 */
fun <T : View> Activity.findView(@IdRes res: Int): Lazy<T> {
    return lazy { findViewById<T>(res) }
}