package com.leon.lgank.model;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/8
 * 描述:
 */
public class GankModel {

    /**
     * error : false
     * results : [{"_id":"592b8475421aa92c769a8bd6","createdAt":"2017-05-29T10:16:21.331Z","desc":"给初学者的RxJava2.0教程（七）: Flowable","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484711&idx=1&sn=c3837b7cad21f0a69d7dccd1aaaf7721&chksm=96cda46aa1ba2d7ce145472449e5a832cd3ac0bc1f766fe09f1ce68ca46c16bab645e507f0b5#rd","used":true,"who":"陈宇明"},{"_id":"59362bba421aa92c79463369","createdAt":"2017-06-06T12:12:42.342Z","desc":"Android之View的诞生之谜","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"http://url.cn/4A5S9LZ","used":true,"who":"陈宇明"},{"_id":"59373fe9421aa92c7be61bb7","createdAt":"2017-06-07T07:51:05.383Z","desc":"Android之自定义View的死亡三部曲之Measure","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"http://url.cn/4A7XYzE","used":true,"who":"陈宇明"},{"_id":"5937531a421aa92c7946336e","createdAt":"2017-06-07T09:12:58.439Z","desc":"Using ThreadPoolExecutor in Android","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://blog.mindorks.com/threadpoolexecutor-in-android-8e9d22330ee3","used":true,"who":"AMIT SHEKHAR"},{"_id":"59376824421aa92c769a8c22","createdAt":"2017-06-07T10:42:44.144Z","desc":"Kotlin 泛型定义与 Java 类似，但有着更多特性支持。","images":["http://img.gank.io/56aca21b-3f5c-46fb-82d0-023e0c39010c"],"publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://kymjs.com/code/2017/06/06/01/","used":true,"who":"张涛"},{"_id":"59376b8e421aa92c79463372","createdAt":"2017-06-07T10:57:18.745Z","desc":"Android异步的姿势，你真的用对了吗？","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzI3OTU3OTQ1Mw==&mid=2247483791&idx=1&sn=a0062d053cc86ebe2c8d8b34fcc3b985&chksm=eb44dfdddc3356cba30b56921577b4d5a63c28d589277634406e688b658e4eaeca01db2bed0c&mpshare=1&scene=23&srcid=0607Zswt0KY7vLEUnpGGGJpb#rd","used":true,"who":null},{"_id":"5937735f421aa92c769a8c24","createdAt":"2017-06-07T11:30:39.352Z","desc":"Android 高质量录音库。","publishedAt":"2017-06-07T11:43:31.396Z","source":"chrome","type":"Android","url":"https://github.com/lrannn/SimpleRecorder","used":true,"who":"代码家"},{"_id":"59377396421aa92c79463374","createdAt":"2017-06-07T11:31:34.749Z","desc":"Android 边缘侧滑效果，支持多种场景下的侧滑退出。","images":["http://img.gank.io/7978f10c-f0cc-4ec9-9124-78510138beac"],"publishedAt":"2017-06-07T11:43:31.396Z","source":"chrome","type":"Android","url":"https://github.com/GeeJoe/EdgeSlidingBack","used":true,"who":"代码家"},{"_id":"59325657421aa92c73b647b2","createdAt":"2017-06-03T14:25:27.460Z","desc":"知乎和简书的Android客户端夜间模式实现方式","images":["http://img.gank.io/6a7e84a9-214e-42b2-9234-c64b192359eb"],"publishedAt":"2017-06-06T11:36:13.568Z","source":"web","type":"Android","url":"http://blog.coderclock.com/2016/08/28/android/%E7%9F%A5%E4%B9%8E%E5%92%8C%E7%AE%80%E4%B9%A6%E7%9A%84%E5%A4%9C%E9%97%B4%E6%A8%A1%E5%BC%8F%E5%AE%9E%E7%8E%B0%E5%A5%97%E8%B7%AF/","used":true,"who":"D_clock"},{"_id":"5934998a421aa92c7be61ba0","createdAt":"2017-06-05T07:36:42.966Z","desc":"Android实用View:炫酷的进度条","publishedAt":"2017-06-06T11:36:13.568Z","source":"web","type":"Android","url":"http://url.cn/4A2Vz2i","used":true,"who":"陈宇明"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        /**
         * _id : 592b8475421aa92c769a8bd6
         * createdAt : 2017-05-29T10:16:21.331Z
         * desc : 给初学者的RxJava2.0教程（七）: Flowable
         * publishedAt : 2017-06-07T11:43:31.396Z
         * source : web
         * type : Android
         * url : https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484711&idx=1&sn=c3837b7cad21f0a69d7dccd1aaaf7721&chksm=96cda46aa1ba2d7ce145472449e5a832cd3ac0bc1f766fe09f1ce68ca46c16bab645e507f0b5#rd
         * used : true
         * who : 陈宇明
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String get_id() {
            return _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean getUsed() {
            return used;
        }

        public String getWho() {
            return who;
        }
    }
}
