package com.zkp.androidwan.module.knowledge.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkp.androidwan.R;
import com.zkp.androidwan.bean.ArticleListBean;
import com.zkp.androidwan.utils.ImageLoader;

import java.util.List;

/**
 * @author: zkp
 * @project: Gank
 * @package: com.zkp.gank.module.knowledge.adapter
 * @time: 2019/4/29 14:18
 * @description:
 */
public class KnowledgeListAdapter extends BaseQuickAdapter<ArticleListBean.DataBean.DatasBean, BaseViewHolder> {


    public KnowledgeListAdapter(int layoutResId, @Nullable List<ArticleListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleListBean.DataBean.DatasBean item) {
        helper.setText(R.id.tvArticleTitle, Html.fromHtml(item.getTitle()))
                .setText(R.id.tvArticleAuthor, item.getAuthor())
                .setImageResource(R.id.ivArticleLike, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_like_not);
        if (!TextUtils.isEmpty(item.getChapterName())) {
            String classifyName = item.getSuperChapterName() + " / " + item.getChapterName();
            helper.setText(R.id.tvArticleChapterName, classifyName);
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tvArticleNiceDate, item.getNiceDate());
        }
        helper.getView(R.id.tvArticleTop).setVisibility(item.getType() == 1 ? View.VISIBLE : View.GONE);

        helper.getView(R.id.tvArticleFresh).setVisibility(item.isFresh() ? View.VISIBLE : View.GONE);

        if (item.getTags().size() > 0) {
            helper.setText(R.id.tvArticleTag, item.getTags().get(0).getName()).
                    getView(R.id.tvArticleTag).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tvArticleTag).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.ivArticleThumbnail).setVisibility(View.VISIBLE);
            ImageLoader.load(mContext, item.getEnvelopePic(), helper.getView(R.id.ivArticleThumbnail));
        } else {
            helper.getView(R.id.ivArticleThumbnail).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.ivArticleLike);
    }
}
