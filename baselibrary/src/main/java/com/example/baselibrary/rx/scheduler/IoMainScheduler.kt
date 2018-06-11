package com.example.baselibrary.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alienware on 2018/6/1.
 */
class IoMainScheduler<T>:BaseScheduler<T>(Schedulers.io(),AndroidSchedulers.mainThread())