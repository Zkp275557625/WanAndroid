package com.zkp.androidwan.module.wechat.list;

import com.zkp.androidwan.base.presenter.IPresenter;
import com.zkp.androidwan.base.view.IView;
import com.zkp.androidwan.bean.WeChatArticleBean;

/**
 * @author: zkp
 * @project: Gank
 * @package: com.zkp.gank.module.wechat.list
 * @time: 2019/4/29 15:21
 * @description:
 */
public class WxArticleListFragmentContract {

    public interface View extends IView {

        /**
         * 获取某个微信公众号下的文章列表成功
         *
         * @param data    WeChatArticleBean
         * @param isFresh boolean
         */
        void getWxArticleListSuccess(WeChatArticleBean data, boolean isFresh);

        /**
         * 获取某个微信公众号下的文章列表失败
         *
         * @param errMsg  String
         * @param isFresh boolean
         */
        void getWxArticleListError(String errMsg, boolean isFresh);

        /**
         * 收藏站内文章成功
         */
        void collectArticleSuccess();

        /**
         * 收藏站内文章失败
         *
         * @param errMsg String
         */
        void collectArticleError(String errMsg);

        /**
         * 取消收藏站内文章成功
         */
        void unCollectArticleSuccess();

        /**
         * 取消收藏站内文章失败
         *
         * @param errMsg String
         */
        void unCollectArticleError(String errMsg);

    }

    public interface Presenter extends IPresenter<View> {

        /**
         * 获取某个微信公众号下的文章列表
         *
         * @param id      公账号id int
         * @param page    页码 int
         * @param isFresh 是否刷新 boolean
         */
        void getWxArticleList(int id, int page, boolean isFresh);

        /**
         * 刷新todo列表
         *
         * @param id   公账号id
         */
        void refresh(int id);

        /**
         * 加载更多
         */
        void loadMore();

        /**
         * 收藏站内文章
         *
         * @param id
         */
        void collectArticle(int id);

        /**
         * 取消收藏站内文章
         *
         * @param id
         */
        void unCollectArticle(int id);
    }

}
