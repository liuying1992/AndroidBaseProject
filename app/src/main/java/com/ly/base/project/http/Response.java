package com.ly.base.project.http;

/**
 * Created by Administrator on 2019/9/10 10:04.
 * Email: ly1203575492@163.com
 */
public class Response<T> {
  private int code;//状态码
  private T data;//具体返回数据
  private String msg;//message 接口说明、错误说明

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
