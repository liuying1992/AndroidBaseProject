package com.ly.base.project.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity 基类
 * Created by Administrator on 2019/9/9 17:15.
 * Email: ly1203575492@163.com
 */
public class BaseActivity extends AppCompatActivity {

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
}
