package com.ly.base.project.http;

import com.ly.base.project.util.LogUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求管理类
 * Created by Administrator on 2019/9/9 17:29.
 * Email: ly1203575492@163.com
 */
public class HttpManager {
  private static HttpManager sHttpManager;
  private static Retrofit sRetrofit;
  private static volatile Request sRequest = null;
  private static final int DEFAULT_TIMEOUT = 30;//网络超时时间

  /**
   * 单例
   */
  public static HttpManager getInstance() {
    if (sHttpManager == null) {
      synchronized (HttpManager.class) {
        if (sHttpManager == null) {
          sHttpManager = new HttpManager();
        }
      }
    }
    return sHttpManager;
  }

  /***
   *  初始化相关数据
   */
  public void init() {
    //初始化Okhttp
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging)
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .build();
    //初始化Retrofit
    sRetrofit = new Retrofit.Builder().client(okHttpClient)
        .baseUrl(Urls.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  /***
   * 获取请求实例
   */

  public static Request getRequest() {
    if (sRequest == null) {
      synchronized (Request.class) {
        sRequest = sRetrofit.create(Request.class);
      }
    }
    return sRequest;
  }

  /**
   * 日志拦截操作
   */
  private class LogInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
      //请求参数拦截
      Request request = chain.request();
      long request_time = System.currentTimeMillis();
      LogUtil.info("Request", request.url() + "" + request.headers());
      //返回参数拦截
      Response response = chain.proceed(request);
      long responst_time = System.currentTimeMillis();
      LogUtil.info("Request", response.request().url() + "" + request.headers());
      return response;
    }
  }
}
