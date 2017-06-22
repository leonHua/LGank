package com.leon.lgank.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/8
 * 描述:
 */
public class GankModel {


    /**
     * error : false
     * results : [{"_id":"592b8466421aa92c7be61b6b","createdAt":"2017-05-29T10:16:06.620Z","desc":"给初学者的RxJava2.0教程(六)：治理Backpressure","images":["http://img.gank.io/56aca21b-3f5c-46fb-82d0-023e0c39010c"],"publishedAt":"2017-06-08T11:27:47.21Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484689&idx=1&sn=1f54c343c6a5d73c63b4cdb555bdf254&chksm=96cda45ca1ba2d4af7e0e51454283f86d145a19b613a1fc149e4d46d22e277c150d2910e95b6#rd","used":true,"who":"陈宇明"},{"_id":"5938a2c9421aa92c769a8c32","createdAt":"2017-06-08T09:05:13.875Z","desc":"snackbar扩展，可以显示进度.","images":["http://img.gank.io/88fb40c1-e9fb-4ffd-9a55-0a822a04f961"],"publishedAt":"2017-06-08T11:27:47.21Z","source":"web","type":"Android","url":"https://github.com/tingyik90/snackprogressbar","used":true,"who":"ShineYang"},{"_id":"5938b347421aa92c7be61bc9","createdAt":"2017-06-08T10:15:35.337Z","desc":" Picasso，Glide，Fresco对比分析","publishedAt":"2017-06-08T11:27:47.21Z","source":"web","type":"Android","url":"http://blog.csdn.net/github_33304260/article/details/70213300","used":true,"who":"libin"},{"_id":"5938c228421aa92c79463380","createdAt":"2017-06-08T11:19:04.518Z","desc":"Android 音乐频谱效果组件","publishedAt":"2017-06-08T11:27:47.21Z","source":"chrome","type":"Android","url":"https://github.com/Taishi-Y/MusicIndicator","used":true,"who":"带马甲"},{"_id":"592b8475421aa92c769a8bd6","createdAt":"2017-05-29T10:16:21.331Z","desc":"给初学者的RxJava2.0教程（七）: Flowable","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484711&idx=1&sn=c3837b7cad21f0a69d7dccd1aaaf7721&chksm=96cda46aa1ba2d7ce145472449e5a832cd3ac0bc1f766fe09f1ce68ca46c16bab645e507f0b5#rd","used":true,"who":"陈宇明"},{"_id":"59362bba421aa92c79463369","createdAt":"2017-06-06T12:12:42.342Z","desc":"Android之View的诞生之谜","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"http://url.cn/4A5S9LZ","used":true,"who":"陈宇明"},{"_id":"59373fe9421aa92c7be61bb7","createdAt":"2017-06-07T07:51:05.383Z","desc":"Android之自定义View的死亡三部曲之Measure","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"http://url.cn/4A7XYzE","used":true,"who":"陈宇明"},{"_id":"5937531a421aa92c7946336e","createdAt":"2017-06-07T09:12:58.439Z","desc":"Using ThreadPoolExecutor in Android","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://blog.mindorks.com/threadpoolexecutor-in-android-8e9d22330ee3","used":true,"who":"AMIT SHEKHAR"},{"_id":"59376824421aa92c769a8c22","createdAt":"2017-06-07T10:42:44.144Z","desc":"Kotlin 泛型定义与 Java 类似，但有着更多特性支持。","images":["http://img.gank.io/56aca21b-3f5c-46fb-82d0-023e0c39010c"],"publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://kymjs.com/code/2017/06/06/01/","used":true,"who":"张涛"},{"_id":"59376b8e421aa92c79463372","createdAt":"2017-06-07T10:57:18.745Z","desc":"Android异步的姿势，你真的用对了吗？","publishedAt":"2017-06-07T11:43:31.396Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzI3OTU3OTQ1Mw==&mid=2247483791&idx=1&sn=a0062d053cc86ebe2c8d8b34fcc3b985&chksm=eb44dfdddc3356cba30b56921577b4d5a63c28d589277634406e688b658e4eaeca01db2bed0c&mpshare=1&scene=23&srcid=0607Zswt0KY7vLEUnpGGGJpb#rd","used":true,"who":null}]
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

    public static class ResultsEntity implements Serializable{
        public ResultsEntity() {
        }

        public ResultsEntity(String _id, String createdAt, String desc, String publishedAt, String source, String type, String url, boolean used, String who, List<String> images) {
            this._id = _id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
            this.images = images;
        }

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

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

        public void setImages(List<String> images) {
            this.images = images;
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

        public List<String> getImages() {
            return images;
        }
    }
}
