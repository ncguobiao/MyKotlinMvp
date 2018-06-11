package com.example.provider.router

/**
 * 模块路由 路径定义
 */
class RouterPath {
   //搜索
    class Search{
        companion object {
            const val PATH_SEARCH = "/searchCenter/search"
        }
    }

    //播放模块
 class Video{
        companion object {
            const val PATH_VIDEO = "/Video/play"
        }
    }

}