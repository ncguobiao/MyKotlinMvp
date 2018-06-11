# MyKotlinMvp
《MyKotlinMvp》 是仿着“开眼Eyepetizer”之前版本并参考《KotlinMvp》开发的一款的短视频小应用，每日为你推荐精选视频，让你大开眼界。本项目采用 Kotlin 语言编写，结合 MVP+Dagger2+RxJava2+Retrofit2+ARouter等的架构设计，在原《KotlinMvp》基础上提供模块化方案，方便大家学习 Kotlin 利用空余时间开发的一款小项目，代码结构清晰有详细注释，如有任何疑问和建议请提 Issue或联系
技术要点
主要使用的第三方开源框架有：

RxJava
RxAndroid
Retrofit
Dagger
ARouter
Glide
Logger
FlycoTabLayout
Flexbox-layout
RealtimeBlurView
SmartRefreshLayout
BGABanner-Android
GSYVideoPlayer
模块说明：

每日精选： 首页采用下拉刷新+RecyclerView 实现，Banner展示每日精选推荐的视频，监听 RecyclerView 的滑动事件，实现 TitleBar 的隐藏显示功能，底部菜单采用高斯模糊的半透明效果，使整个 APP 风格更加清爽。
发现： 包括关注和分类模块，关注是推荐的作者上传的视频集，分类包括时尚、运动、创意、广告、音乐、旅行、生活、记录、开胃、游戏、萌宠、动画、综艺、搞笑等可自由选择想查看的类型视频。
热门： 热门排行榜包括周排行、月排行、总排行的视频列表。
搜索： 根据关键字搜索榜你找到感兴趣的视频。
我的： 个人主页的相关介绍。该Module可以实现模块化，可单独运行调试，给大家提供一点模块化的思想
观看记录： 查看之前看过的视频，按时间进行排序。
更新日志
最新记录请点击查看


去除无用代码，添加观看记录功能。
v1.0

初始化版本，主要功能已经完成。
关于我
Email: 568164681@QQ.com

Thanks
感谢所有优秀的开源项目
Eyepetizer 和 KotlinMvp  给了我很多学习的参考
声明
项目中的 API 均来自开眼视频和开源项目KotlinMvp，纯属学习交流使用，不得用于商业用途！
