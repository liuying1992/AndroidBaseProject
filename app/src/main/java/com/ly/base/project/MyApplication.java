package com.ly.base.project;

import android.app.Application;
import com.ly.base.project.http.HttpManager;

/**
 * Created by Administrator on 2019/9/10 09:29.
 * Email: ly1203575492@163.com
 */
public class MyApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    init();
  }

  /***
   *初始化相关操作
   */
  private void init() {
    HttpManager.getInstance().init();
  }
}
