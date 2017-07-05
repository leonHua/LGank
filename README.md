
![][1]

---

# LGank是一款精致的干货客户端

[下载使用][26]

## 介绍
整体设计遵循 Material Design 规范，实现了多种应用常见的效果。包括不局限于：

 - 沉浸式状态栏
 - 有趣的加载动画
 - 滑动关闭Activity
 - 多主题切换
 - 瀑布流、线性、网格布局动态切换
 - 动态加载流式布局
 - 下拉刷新、上拉加载更多
 - 显示动态GIF图、高斯模糊背景
 - 侧滑菜单
 - Realm数据库使用
 - ViewPage嵌套滑动

提供常见的干货资源获取，支持分类查看，同时提供搜索功能，保留搜索记录。如果仅仅是这样，那么它仅仅又是一个好看的客户端~~

**But, LGank 同时提供资讯阅读模块，可以查看IT移动互联最新资讯。而且提供干货收藏功能，再也不用担心看过的东东找不到啦~~~** 

**福利：在设置界面可以将头像、昵称、我的博客和Follow Me(GitHub、微博等等)设置为你自己的地址，完全将其打造成属于自己的专属客户端。**

更多等你发现........

## 实现方案

架构：MVC+ViewPager+Fragment
网络：RxJava+Retorfit+OkHttp3
数据库：Realm
图片加载：Glide
滑动关闭Activity：swipebackhelper
多主题换肤：skin-support
图片选择：boxing
快速实现设置界面：[lsettingviewlibrary][2]
.........（更多请查看项目依赖文件）

@感谢以上开源作者无私贡献，感谢 [干货集中营][3] 提供数据

## 项目效果图（动图加载失败可以查看screenshot文件夹下）

![](screenshot/lgankgif1.gif)
![](screenshot/lgankgif2.gif)

----------

![][6] 
![][7] 
![][8] 

----------

![][9] 
![][10] 
![][11] 

----------

![][12] 
![][13] 
![][14]
----------
![][15] 
![][16] 
![][17] 

----------

![][18] 
![][19] 
![][20] 


## 打赏鼓励
资讯获取有限制（次数和频率），如发现资讯无法读取，可能是免费次数超限了~~~ (需要更换Constant类里的APIKEY-天行数据)

如果你觉得我的项目对你有帮助，请扫描下方的二维码随意打赏，您的支持是我最好的动力! 

不想打赏，那就随手来个star吧！

![Alipay支付宝][21] ......................... ![WeChat微信][22]




## 关于我
[我的简书][23]

[我的博客][24]

[我的GitHub][25]

## License

Copyright (C) 2017 leonHua

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


  [1]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/ic_launcher.png
  [2]: https://github.com/leonHua/LSettingView
  [3]: http://gank.io/
  [6]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%281%29.png?imageView2/0/w/500/h/1200/q/100
  [7]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%282%29.png?imageView2/0/w/500/h/1200/q/100
  [8]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%283%29.png?imageView2/0/w/500/h/1200/q/100
  [9]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%284%29.png?imageView2/0/w/500/h/1200/q/100
  [10]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%285%29.png?imageView2/0/w/500/h/1200/q/100
  [11]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%286%29.png?imageView2/0/w/500/h/1200/q/100
  [12]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%287%29.png?imageView2/0/w/500/h/1200/q/100
  [13]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%288%29.png?imageView2/0/w/500/h/1200/q/100
  [14]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%289%29.png?imageView2/0/w/500/h/1200/q/100
  [15]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%2810%29.png?imageView2/0/w/500/h/1200/q/100
  [16]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%2811%29.png?imageView2/0/w/500/h/1200/q/100
  [17]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%2812%29.png?imageView2/0/w/500/h/1200/q/100
  [18]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%2813%29.png?imageView2/0/w/500/h/1200/q/100
  [19]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%2814%29.png?imageView2/0/w/500/h/1200/q/100
  [20]: http://o9w936rbz.bkt.clouddn.com/github/img/LGank/lgank%20%2815%29.png?imageView2/0/w/500/h/1200/q/100
  [21]: http://o9w936rbz.bkt.clouddn.com/me/zhifubao.png
  [22]: http://o9w936rbz.bkt.clouddn.com/me/weixin.png
  [23]: http://www.jianshu.com/u/984760f279b0
  [24]: http://dujinghua.cn/
  [25]: https://github.com/leonHua
  [26]: https://fir.im/lgank
