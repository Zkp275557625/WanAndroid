package com.zkp.androidwan.module.knowledge.detail.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zkp.androidwan.R;
import com.zkp.androidwan.app.App;
import com.zkp.androidwan.base.fragment.BaseFragment;
import com.zkp.androidwan.bean.ArticleListBean;
import com.zkp.androidwan.module.home.detail.ArticleDetailActivity;
import com.zkp.androidwan.module.knowledge.adapter.KnowledgeListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author: zkp
 * @project: Gank
 * @package: com.zkp.gank.module.knowledge.detail.list
 * @time: 2019/4/29 13:54
 * @description:
 */
public class KnowledgeListFragment extends BaseFragment<KnowledgeListPresenter> implements KnowledgeListFragmentContract.View {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    /**
     * 分类的id，上述二级目录的id
     */
    private int cid;

    private int page = 0;

    private KnowledgeListAdapter mAdapter;

    public static KnowledgeListFragment newInstance(Bundle bundle) {
        KnowledgeListFragment fragment = new KnowledgeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy_list;
    }

    @Override
    protected void initView() {

        App.getApplication().addFragment(this);

        List<ArticleListBean.DataBean.DatasBean> mArticleList = new ArrayList<>();
        mAdapter = new KnowledgeListAdapter(R.layout.item_home_article, mArticleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new KnowledgeListPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        cid = getArguments().getInt("cid");

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 0;
            mPresenter.getArticleList(page, cid, true);
            refreshLayout.finishRefresh();
        });

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.getArticleList(++page, cid, false);
            refreshLayout.finishLoadMore();
        });

        mPresenter.getArticleList(page, cid, true);

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

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.ivArticleLike:
                    if (Objects.requireNonNull(mAdapter.getItem(position)).isCollect()) {
                        mPresenter.unCollectArticle(Objects.requireNonNull(mAdapter.getItem(position)).getId());
                    } else {
                        mPresenter.collectArticle(Objects.requireNonNull(mAdapter.getItem(position)).getId());
                    }
                    break;
                default:
                    break;
            }
        });

    }

    @Override
    public void getArticleListSuccess(ArticleListBean data, boolean isFresh) {
        if (mAdapter == null) {
            return;
        }
        if (isFresh) {
            mAdapter.replaceData(data.getData().getDatas());
        } else {
            mAdapter.addData(data.getData().getDatas());
        }
    }

    @Override
    public void getArticleListError(String errMsg, boolean isFresh) {
        SmartToast.show(errMsg);
    }

    @Override
    public void collectArticleSuccess() {
        page = 0;
        mPresenter.getArticleList(page, cid, true);
    }

    @Override
    public void collectArticleError(String errMsg) {
        SmartToast.show(errMsg);
    }

    @Override
    public void unCollectArticleSuccess() {
        page = 0;
        mPresenter.getArticleList(page, cid, true);
    }

    @Override
    public void unCollectArticleError(String errMsg) {
        SmartToast.show(errMsg);
    }

    public void jumpToTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
