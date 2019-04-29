package com.zkp.gank.module.wechat.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zkp.gank.R;
import com.zkp.gank.base.fragment.BaseFragment;
import com.zkp.gank.bean.WeChatArticleBean;
import com.zkp.gank.module.home.detail.ArticleDetailActivity;
import com.zkp.gank.module.wechat.adapter.WxArticleListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author: zkp
 * @project: Gank
 * @package: com.zkp.gank.module.wechat.list
 * @time: 2019/4/29 15:21
 * @description:
 */
public class WxArticleListFragment extends BaseFragment<WxArticleListPresenter> implements WxArticleListFragmentContract.View {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    /**
     * 公众号id、
     */
    private int id;
    private int page = 0;

    private WxArticleListAdapter mAdapter;

    public static WxArticleListFragment newInstance(Bundle bundle) {
        WxArticleListFragment fragment = new WxArticleListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat_list;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        List<WeChatArticleBean.DataBean.DatasBean> dataBeanList = new ArrayList<>();
        mAdapter = new WxArticleListAdapter(R.layout.item_home_article, dataBeanList);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        id = getArguments().getInt("id");

        mPresenter = new WxArticleListPresenter();
        mPresenter.attachView(this);
        mPresenter.getWxArticleList(id, page, true);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getWxArticleList(id, page, true);
            refreshLayout.finishRefresh();
        });

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.getWxArticleList(id, ++page, false);
            refreshLayout.finishLoadMore();
        });

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
                return;
            }
            Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
            intent.putExtra("title", mAdapter.getData().get(position).getTitle());
            intent.putExtra("articleLink", mAdapter.getData().get(position).getLink());
            intent.putExtra("articleId", mAdapter.getData().get(position).getId());
            intent.putExtra("isCollected", mAdapter.getData().get(position).isCollect());
            intent.putExtra("isShowCollectIcon", true);
            intent.putExtra("articleItemPosition", position);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
    }

    @Override
    public void getWxArticleListSuccess(WeChatArticleBean data, boolean isFresh) {
        if (mAdapter == null) {
            return;
        }
        if (isFresh) {
//            mArticleList = articleListData.getDatas();
            mAdapter.replaceData(data.getData().getDatas());
        } else {
//            mArticleList.addAll(articleListData.getDatas());
            mAdapter.addData(data.getData().getDatas());
        }
    }

    @Override
    public void getWxArticleListError(String errMsg, boolean isFresh) {
        SmartToast.show(errMsg);
    }

    public void jumpToTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
