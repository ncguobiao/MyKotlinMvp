package com.example.discovery.data.bean

import java.io.Serializable

/**
 * Created by Alienware on 2018/6/7.
 * 分类 Bean
 */
data class CategoryBean(val id: Long
                        , val name: String
                        , val description: String
                        , val bgPicture: String
                        , val bgColor: String
                        , val headerImage: String)
    : Serializable