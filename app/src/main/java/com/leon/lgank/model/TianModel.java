package com.leon.lgank.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/26
 * 描述:
 */
public class TianModel {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-06-06 15:26","title":"摩拜接入苹果支付服务 iPhone相机可直接扫码解","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/51d882e6322f4250abcd1b920e870e0f20170606152639.png","url":"http://tech.163.com/17/0606/15/CM8P59DV00097U7R.html"},{"ctime":"2017-06-06 14:50","title":"未按时缴纳物业费 乐视视频员工或被禁止进入大","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/b18b0a61791a4dd98c173b1a59546a7120170606144951.png","url":"http://tech.163.com/17/0606/14/CM8N2S8L00097U7R.html"},{"ctime":"2017-06-06 13:05","title":"特斯拉每卖一辆亏1.3万美元 卖得越多亏得越多","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/0c40a2ad95d94fb9a06c2bf795adf72320170606103032.png","url":"http://tech.163.com/17/0606/13/CM8H2GVP00097U7R.html"},{"ctime":"2017-06-06 13:15","title":"哈佛撤销10名新生录取资格：在网上发布极端言论","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/69a29ab38ba14449889552363992767d20170606110755.png","url":"http://tech.163.com/17/0606/13/CM8HKO7L00097U7R.html"},{"ctime":"2017-06-06 13:18","title":"用环保快递包裹一天可少砍十万棵树，为何推广难","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/84fc17a3f8ac4fcf88fa9e7792e2e33420170606113715.jpeg","url":"http://tech.163.com/17/0606/13/CM8HR7UD00097U7R.html"},{"ctime":"2017-06-06 13:29","title":"Google砍了好多业务，CFO讲述决策制定过程","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/f224332db5384b6d9757762cfa3b132a20170606131831.png","url":"http://tech.163.com/17/0606/13/CM8IDNON00097U7R.html"},{"ctime":"2017-06-06 13:41","title":"Snap1.25亿美元收购基于LBS广告分析的创业公司","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/61bb5ccb12a445fcb492496856ec309020170606134043.png","url":"http://tech.163.com/17/0606/13/CM8J3NG500097U7R.html"},{"ctime":"2017-06-06 11:05","title":"中国互金协会信披平台上线 首批10家试点单位接","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/e9a678aee27840709ec30fe70fbda2e020170606091935.jpeg","url":"http://tech.163.com/17/0606/11/CM8A6EMR00097U7R.html"},{"ctime":"2017-06-06 11:08","title":"B站年轻人：玩鬼畜或圈地自萌，正打破\u201c次元壁","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/0c40a2ad95d94fb9a06c2bf795adf72320170606103032.png","url":"http://tech.163.com/17/0606/11/CM8AD2BS00097U7R.html"},{"ctime":"2017-06-06 11:22","title":"百度搜索\u201c找人\u201d为何搜到行骗公司？","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/69a29ab38ba14449889552363992767d20170606110755.png","url":"http://tech.163.com/17/0606/11/CM8B5U7A00097U7R.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistEntity> newslist;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setNewslist(List<NewslistEntity> newslist) {
        this.newslist = newslist;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<NewslistEntity> getNewslist() {
        return newslist;
    }

    public static class NewslistEntity implements Serializable{
        /**
         * ctime : 2017-06-06 15:26
         * title : 摩拜接入苹果支付服务 iPhone相机可直接扫码解
         * description : 移动互联
         * picUrl : http://cms-bucket.nosdn.127.net/51d882e6322f4250abcd1b920e870e0f20170606152639.png
         * url : http://tech.163.com/17/0606/15/CM8P59DV00097U7R.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCtime() {
            return ctime;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getUrl() {
            return url;
        }
    }
}
