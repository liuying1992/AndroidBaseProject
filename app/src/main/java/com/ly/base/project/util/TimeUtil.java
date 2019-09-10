package com.ly.base.project.util;

/**
 * Created by Administrator on 2019/9/10 14:52.
 * Email: ly1203575492@163.com
 */

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *
 * @author way
 */
@SuppressLint("SimpleDateFormat") public class TimeUtil {

  public static String getRestTime(long getTime) {
    if (getTime == 0) {
      return "";
    }
    long rest = System.currentTimeMillis() - getTime;
    SimpleDateFormat format = new SimpleDateFormat("d天H小时m分");
    return format.format(new Date(rest));
  }

  public static String getTime(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    return format.format(new Date(time));
  }

  public static String getTimerYMDStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    return format.format(new Date(time));
  }

  public static String getTimerShortYMDStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
    return format.format(new Date(time));
  }

  public static Date getLongToDate(long time) {
    return new Date(time);
  }

  public static String getTimerYMD(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(new Date(time));
  }

  public static String getTimerYMDHM(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return format.format(new Date(time));
  }

  public static String getTimeYMDMSStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return format.format(new Date(time));
  }

  public static String getTimerYMStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
    return format.format(new Date(time));
  }

  public static String getTimerMDStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
    return format.format(new Date(time));
  }

  public static String getMDStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("MM-dd");
    return format.format(new Date(time));
  }

  public static String getTimeStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
    return format.format(new Date(time));
  }

  public static String getTimeOneMonthStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy年M月");
    return format.format(new Date(time));
  }

  public static String getTimeArabStr(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    return format.format(new Date(time));
  }

  public static String getHourAndMin(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(new Date(time));
  }

  public static String getMinAndSeconds(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    return format.format(new Date(time));
  }

  public static String getYMDHMTime(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd HH:mm");
    return format.format(date);
  }

  public static String getMDHMTime(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
    return format.format(new Date(time));
  }

  public static String getYMDTime(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    return format.format(date);
  }

  public static String getYMTime(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
    return format.format(date);
  }

  public static String getYMTime(long date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
    return format.format(date);
  }

  public static String getYTime(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");
    return format.format(date);
  }

  public static String getFirstRegDate(long time) {
    if (time == 0) {
      return "";
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy年上牌");
    return format.format(new Date(time));
  }

  //判断时间先后
  public static boolean isDateBefore(String date1, String date2) {
    try {
      SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
      return df.parse(date1).before(df.parse(date2));
    } catch (ParseException e) {
      return false;
    }
  }

  //判断时间先后
  public static boolean isDateBefore(String date1) {
    try {
      Date currentTime = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
      String dateString = df.format(currentTime);
      if (date1.equals(dateString)) {
        return true;
      }
      return df.parse(date1).before(df.parse(dateString));
    } catch (ParseException e) {
      return false;
    }
  }

  public static boolean DateBefore(String date1) {
    try {
      Date currentTime = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
      String dateString = df.format(currentTime);
      if (date1.equals(dateString)) {
        return true;
      }
      return df.parse(date1).before(df.parse(dateString));
    } catch (ParseException e) {
      return false;
    }
  }

  public static String getChatTime(long timesamp) {
    String result = "";
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    Date today = new Date(System.currentTimeMillis());
    Date otherDay = new Date(timesamp);
    int temp = Integer.parseInt(sdf.format(today)) - Integer.parseInt(sdf.format(otherDay));

    switch (temp) {
      case 0:
        result = "今天 " + getHourAndMin(timesamp);
        break;
      case 1:
        result = "昨天 " + getHourAndMin(timesamp);
        break;
      case 2:
        result = "前天 " + getHourAndMin(timesamp);
        break;

      default:
        // result = temp + "天前 ";
        result = getTime(timesamp);
        break;
    }

    return result;
  }

  public static long stringToLong(String strTime, String formatType) throws ParseException {
    Date date = stringToDate(strTime, formatType); // String类型转成date类型
    if (date == null) {
      return 0;
    } else {
      long currentTime = dateToLong(date); // date类型转成long类型
      return currentTime;
    }
  }

  public static long dateToLong(Date date) {
    return date.getTime();
  }

  public static Date stringToDate(String strTime, String formatType) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat(formatType);
    Date date = null;
    date = formatter.parse(strTime);
    return date;
  }

  public static String getCarTime(long times) {
    String result;
    Date date = new Date(times);
    //        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 用现在距离1970年的时间间隔new
    // Date().getTime()减去以前的时间距离1970年的时间间隔date.getTime()得出的就是以前的时间与现在时间的时间间隔
    long time = new Date().getTime() - date.getTime();// 得出的时间间隔是毫秒
    //        System.out.println(time + "间隔");
    if (time / 1000 < 10 && time / 1000 >= 0) {
      // 如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒
      result = "刚刚";
    } else if (time / 1000 < 60 && time / 1000 > 0) {
      // 如果时间间隔小于60秒则显示多少秒前
      int se = (int) ((time % 60000) / 1000);
      result = se + "秒前";
    } else if (time / 60000 < 60 && time / 60000 > 0) {
      // 如果时间间隔小于60分钟则显示多少分钟前
      int m = (int) ((time % 3600000) / 60000);// 得出的时间间隔的单位是分钟
      result = m + "分前";
    } else if (time / 3600000 < 24 && time / 3600000 >= 0) {
      // 如果时间间隔小于24小时则显示多少小时前
      int h = (int) (time / 3600000);// 得出的时间间隔的单位是小时
      result = h + "小时前";
    } else if (time / 86400000 < 30 && time / 86400000 >= 0) {
      // 如果时间间隔小于3天则显示多少天前
      int h = (int) (time / 86400000);// 得出的时间间隔的单位是天
      result = h + "天前";
    } else if (time / 2592000000l < 12 && time / 2592000000l >= 0) {
      // 大于30天，则显示正常的时间，但是不显示秒
      int m = (int) (time / 2592000000l);// 得出的时间间隔的单位是天
      result = m + "月前";
    } else {
      int y = (int) (time / 2592000000l * 12);
      result = y + "年前";
    }
    return result;
  }

  public static Long getNowLongDate() {
    return TimeUtil.dateToLong(new Date());
  }

  /**
   * 毫秒转换 mm：ss格式方法
   */
  public static String converLongTimeToStr(long time) {
    int ss = 1000;
    int mi = ss * 60;
    int hh = mi * 60;

    long hour = (time) / hh;
    long minute = (time - hour * hh) / mi;
    long second = (time - hour * hh - minute * mi) / ss;

    String strHour = hour < 10 ? "0" + hour : "" + hour;
    String strMinute = minute < 10 ? "0" + minute : "" + minute;
    String strSecond = second < 10 ? "0" + second : "" + second;
    if (hour > 0) {
      return strHour + ":" + strMinute + ":" + strSecond;
    } else {
      return strMinute + ":" + strSecond;
    }
  }

  public static String getYTime(Long date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");
    return format.format(date);
  }

  public static String getMTime(Long date) {
    SimpleDateFormat format = new SimpleDateFormat("M");
    return format.format(date);
  }
}
