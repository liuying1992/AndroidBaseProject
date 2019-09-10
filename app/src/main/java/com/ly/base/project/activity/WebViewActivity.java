package com.ly.base.project.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ly.base.project.R;
import com.ly.base.project.base.BaseActivity;
import com.ly.base.project.common.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 加载web
 * Created by Administrator on 2019/9/10 15:04.
 * Email: ly1203575492@163.com
 */
public class WebViewActivity extends BaseActivity {
  @BindView(R.id.progressbar) ProgressBar mProgressBar;
  @BindView(R.id.webView) WebView mWebView;
  private String url;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.common_web);
    ButterKnife.bind(this);
    url = getIntent().getStringExtra(Constants.WEB_URL);
    WebSettings setting = mWebView.getSettings();
    setting.setJavaScriptEnabled(true);
    setting.setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
    //setting.setBuiltInZoomControls(true); // 原网页基础上缩放
    //setting.setUseWideViewPort(true);
    //setting.setSupportZoom(true);//支持缩放
    setting.setLoadWithOverviewMode(true);
    setting.setDomStorageEnabled(true);
    setting.setBlockNetworkImage(false);
    setting.setBlockNetworkLoads(false);
    setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
    mWebView.setHorizontalScrollBarEnabled(false);// 水平不显示
    mWebView.setVerticalScrollBarEnabled(false); // 垂直不显示
    setPageCacheCapacity(setting);

    mWebView.addJavascriptInterface(this, "Resize");
    mWebView.addJavascriptInterface(this, "TDNT");

    setWebViewClient();

    loadWebPage(url);
  }

  @Override protected Toolbar getToolbar() {
    return null;
  }

  /**
   * 当进行goBack的时候 使用前一个页面的缓存 避免每次都重新载入
   *
   * @param webSettings webView的settings
   */
  protected void setPageCacheCapacity(WebSettings webSettings) {
    try {
      Class<?> c = Class.forName("android.webkit.WebSettingsClassic");
      if (null != c) {
        Method tt = c.getMethod("setPageCacheCapacity", new Class[] { int.class });
        if (null != tt) {
          tt.invoke(webSettings, 5);
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private void setWebViewClient() {
    mWebView.setWebViewClient(new WebViewClient() {

      @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mProgressBar != null) mProgressBar.setVisibility(View.VISIBLE);
      }

      @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
      }

      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        mWebView.loadUrl(url);
        computeHeight();
        return true;
      }

      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        computeHeight();
        if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
      }

      @Override
      public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();  // 接受所有网站的证书
      }
    });
    mWebView.setWebChromeClient(new WebChromeClient() {
      @Override public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (mProgressBar != null) mProgressBar.setProgress(newProgress);
        if (newProgress > 92) {
          if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
        }
      }

      @Override public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        //setTitle(title);
      }

      @Override
      public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
      }
    });
  }

  private void computeHeight() {
    if (mWebView != null) {
      mWebView.loadUrl(
          "javascript:Resize.fetchHeight(document.body.getBoundingClientRect().height)");
    }
  }

  /**
   * 加载网页
   */
  private void loadWebPage(String params) {
    if (this.isUrl(params)) { // url 地址 形式 加载
      //WebView加载web资源
      //          final String urlString = WebPage.IP_PATH
      //                  + this.webPageIdentify
      //                  + this.webPageParams;
      mWebView.loadUrl(params);
    } else {// 网页 源码 形式 加载
      mWebView.loadDataWithBaseURL(null, params, "text/html", "utf-8", null);
    }
  }

  private boolean isUrl(final String url) {
    final String strTmp = url.substring(0, 4);
    if (!TextUtils.isEmpty(strTmp)) {
      if ("http".equals(strTmp) || "https".equals(strTmp)) {
        return true;
      }
    }

    return false;
  }
}
