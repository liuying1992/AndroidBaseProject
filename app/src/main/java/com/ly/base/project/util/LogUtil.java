package com.ly.base.project.util;

import android.util.Log;
import com.ly.base.project.BuildConfig;

/**
 * Created by Administrator on 2018/12/18.
 * Description：
 */
public class LogUtil {

  private LogUtil() {
    throw new UnsupportedOperationException("can't instantiate...");
  }

  /**
   * debug构建显示日志信息
   */
  public static void info(String tag, String info) {
    if (BuildConfig.DEBUG) {
      Log.i(tag, info);
    }
  }

  public static void error(String tag, String info) {
    if (BuildConfig.DEBUG) {
      Log.e(tag, info);
    }
  }
}
