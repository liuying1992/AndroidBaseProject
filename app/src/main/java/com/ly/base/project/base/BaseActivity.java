package com.ly.base.project.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
  }

  public void onBackClick(View view) {
    finish();
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.base_activity);
    mContext = this;
  }

  public void setTitleText(String title) {
    mToolbar = getToolbar();
    if (mToolbar != null) {
      mTitle = mToolbar.findViewById(R.id.txt_title);
      mBackText = mToolbar.findViewById(R.id.txt_back);
    }
    if (mToolbar != null) {
      mTitle.setText(title);
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
