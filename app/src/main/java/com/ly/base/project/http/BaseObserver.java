package com.ly.base.project.http;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2019/9/10 10:46.
 * Email: ly1203575492@163.com
 */
public abstract class BaseObserver<T> extends DisposableObserver<ResponseModel<T>> {

  protected abstract void onSuccess(ResponseModel<T> response);

  protected abstract void onError(String msg);

  @Override public void onNext(ResponseModel<T> tResponse) {
    onSuccess(tResponse);
  }

  @Override public void onComplete() {

  }

  @Override public void onError(Throwable e) {
    onError(e.toString());
  }
}
