package com.ly.base.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ly.base.project.activity.WebViewActivity;
import com.ly.base.project.api.HomeApi;
import com.ly.base.project.base.BaseActivity;
import com.ly.base.project.common.Constants;
import com.ly.base.project.http.BaseObserver;
import com.ly.base.project.http.HttpManager;
import com.ly.base.project.http.ResponseModel;
import com.ly.base.project.model.Datas;
import com.ly.base.project.model.Index;
import com.ly.base.project.util.TimeUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Launch 首页
 */
public class MainActivity extends BaseActivity {

  @BindView(R.id.btn_request) Button mBtnRequest;
  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  @BindView(R.id.img_back) ImageView mImgBack;
  @BindView(R.id.txt_back) TextView mTxtBack;
  @BindView(R.id.txt_title) TextView mTxtTitle;
  @BindView(R.id.tool_bar) Toolbar mToolBar;
  private BaseQuickAdapter<Datas, BaseViewHolder> mBaseQuickAdapter;
  private List<Datas> datas;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setTitleText("AndroidBaseProject");

    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
    mRecyclerView.setLayoutManager(layoutManager);
    mBaseQuickAdapter = new BaseQuickAdapter<Datas, BaseViewHolder>(R.layout.item_wan_android) {
      @Override protected void convert(BaseViewHolder helper, Datas item) {
        Glide.with(mContext)
            .load(item.getEnvelopePic())
            .into((ImageView) helper.getView(R.id.item_pic));
        helper.setText(R.id.item_title, item.getTitle())
            .setText(R.id.item_content, item.getDesc())
            .setText(R.id.item_time, TimeUtil.getTimerYMDStr(item.getPublishTime()))
            .setText(R.id.item_user, item.getAuthor());
      }
    };
    mBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent bundle = new Intent(mContext, WebViewActivity.class);
        bundle.putExtra(Constants.WEB_URL, datas.get(position).getLink());
        startActivity(bundle);
      }
    });
    mRecyclerView.setAdapter(mBaseQuickAdapter);
  }

  @Override protected Toolbar getToolbar() {
    return mToolBar;
  }

  @OnClick({ R.id.btn_request }) public void onClickListener(View view) {
    switch (view.getId()) {
      case R.id.btn_request:
        doRequest();
        break;
    }
  }

  private void doRequest() {
    HttpManager.getInstance()
        .getService(HomeApi.class)
        .index()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseObserver<Index>() {
          @Override protected void onSuccess(ResponseModel<Index> response) {
            datas = response.getData().getDatas();
            mBaseQuickAdapter.setNewData(datas);
          }

          @Override protected void onError(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
          }
        });
  }
}
