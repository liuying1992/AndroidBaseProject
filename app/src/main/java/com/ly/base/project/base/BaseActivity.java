package com.ly.base.project.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.ly.base.project.R;

/**
 * BaseActivity 基类
 * Created by Administrator on 2019/9/9 17:15.
 * Email: ly1203575492@163.com
 */
public abstract class BaseActivity extends AppCompatActivity {
  public Context mContext;
  private Toolbar mToolbar;
  private TextView mTitle, mBackText;
  private ImageView mBackImg;

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
  }

  public void onBackClick(View view) {
    finish();
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = this;
  }

  public void setTitleText(String title) {
    initToolBar();
    if (mToolbar != null) {
      mTitle.setText(title);
    }
  }

  public void setTitleBackHide(boolean hide) {
    initToolBar();
    if (mToolbar != null) {
      mBackImg.setVisibility(hide ? View.GONE : View.VISIBLE);
      mBackText.setVisibility(hide ? View.GONE : View.VISIBLE);
    }
  }

  public void initToolBar() {
    if (mToolbar == null) {
      mToolbar = getToolbar();
    }
    if (mToolbar != null) {
      mTitle = mToolbar.findViewById(R.id.txt_title);
      mBackText = mToolbar.findViewById(R.id.txt_back);
      mBackImg = mToolbar.findViewById(R.id.img_back);
    }
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  protected abstract Toolbar getToolbar();
}
