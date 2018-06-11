package com.example.baselibrary.data.net.execption

import java.lang.RuntimeException

/**
 * Created by Alienware on 2018/6/1.
 */
class ApiException:RuntimeException {
    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}