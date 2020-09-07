package com.ly.base.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ly.base.project.activity.RetrofitActivity;
import com.ly.base.project.base.BaseActivity;

/**
 * Launch 首页
 */
public class MainActivity extends BaseActivity {

  @BindView(R.id.btn_request) Button mBtnRequest;

  @BindView(R.id.img_back) ImageView mImgBack;
  @BindView(R.id.txt_back) TextView mTxtBack;
  @BindView(R.id.txt_title) TextView mTxtTitle;
  @BindView(R.id.tool_bar) Toolbar mToolBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setTitleText("AndroidBaseProject");
    setTitleBackHide(true);
  }

  @Override protected Toolbar getToolbar() {
    return mToolBar;
  }

  @OnClick({ R.id.btn_request }) public void onClickListener(View view) {
    switch (view.getId()) {
      case R.id.btn_request:
        startActivity(new Intent(mContext, RetrofitActivity.class));
        break;

        //1.first commit
      //2.second commit
        //1. dev commit
    }
  }
}
