package com.example.provider.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.alibaba.fastjson.JSON
import java.lang.reflect.Type





/**
 * Created by Alienware on 2018/6/4.
 */
@Route(path = "/service/json")
class JsonServiceImpl : SerializationService {
    override fun init(context: Context) {

    }

    override fun <T> json2Object(text: String, clazz: Class<T>): T {
        return JSON.parseObject(text, clazz)
    }

    override fun object2Json(instance: Any): String {
        return JSON.toJSONString(instance)
    }

    override fun <T> parseObject(input: String, clazz: Type): T {
        return JSON.parseObject(input, clazz)
    }
}
