package com.ly.base.project.api;

import com.ly.base.project.http.ResponseModel;
import com.ly.base.project.model.Index;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2019/9/10 10:33.
 * Email: ly1203575492@163.com
 */
public interface HomeApi {

  @GET("/article/listproject/{page}/json") Observable<ResponseModel<Index>> index(
      @Path("page") int page);
}
