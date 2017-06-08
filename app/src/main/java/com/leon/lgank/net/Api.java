package com.leon.lgank.net;

import com.leon.lgank.model.GankModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述: API接口
 */
public interface Api {

    /**
     * 获取分类数据
     * 示例：http://gank.io/api/data/Android/10/1
     *
     * @param category 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param pageSize 请求个数： 数字，大于0
     * @param page     第几页：数字，大于0
     * @return
     */
    @GET("data/{category}/{pageSize}/{page}")
    Observable<GankModel> getCategoryData(@Path("category") String category,
                                          @Path("pageSize") int pageSize,
                                          @Path("page") int page);

}
