package com.example.provider.common

/**
 * 业务常量
 */
class Constants private constructor(){
    companion object {
        const val BUNDLE_VIDEO_DATA = "video_data"
        const val BUNDLE_CATEGORY_DATA = "category_data"

        //腾讯 Bugly APP id
        const val BUGLY_APPID = "176aad0d9e"


        //sp 存储的文件名
        const val FILE_WATCH_HISTORY_NAME = "watch_history_file"   //观看记录

        const val FILE_COLLECTION_NAME = "collection_file"    //收藏视屏缓存的文件名

        val IMG_TRANSITION = "IMG_TRANSITION"
        val TRANSITION = "TRANSITION"
    }
}