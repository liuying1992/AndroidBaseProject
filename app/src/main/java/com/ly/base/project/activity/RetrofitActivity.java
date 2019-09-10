package com.ly.base.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ly.base.project.R;
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
 * Created by Administrator on 2019/9/10 16:18.
 * Email: ly1203575492@163.com
 */
public class RetrofitActivity extends BaseActivity {

  @BindView(R.id.tool_bar) Toolbar mToolBar;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  private BaseQuickAdapter<Datas, BaseViewHolder> mBaseQuickAdapter;
  private List<Datas> datas;
  private int page = 0;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_retrofit);
    ButterKnife.bind(this);
    setTitleText("玩Android");

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
    mBaseQuickAdapter.bindToRecyclerView(mRecyclerView);

    mBaseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
      @Override public void onLoadMoreRequested() {
        page++;
        doRequest();
      }
    }, mRecyclerView);
    doRequest();

    mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        page = 0;
        doRequest();
      }
    });
  }

  private void doRequest() {
    HttpManager.getInstance()
        .getService(HomeApi.class)
        .index(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseObserver<Index>() {
          @Override protected void onSuccess(ResponseModel<Index> response) {
            mRefreshLayout.setRefreshing(false);
            mBaseQuickAdapter.loadMoreComplete();
            datas = response.getData().getDatas();
            if (page == 0) {
              mBaseQuickAdapter.setNewData(datas);
            } else {
              mBaseQuickAdapter.addData(datas);
            }
            /**如果已经没有更多了 则不可加载更多**/
            if (page == response.getData().getPageCount()) {
              mBaseQuickAdapter.loadMoreEnd(true);
            }
          }

          @Override protected void onError(String msg) {
            mRefreshLayout.setRefreshing(false);
            mBaseQuickAdapter.loadMoreComplete();
            page--;
            if (page < 0) {
              page = 0;
            }
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
          }
        });
  }

  @Override protected Toolbar getToolbar() {
    return mToolBar;
  }
}
