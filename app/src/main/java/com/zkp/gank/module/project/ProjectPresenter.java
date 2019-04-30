package com.zkp.gank.module.project;

import com.zkp.gank.app.GankApplication;
import com.zkp.gank.base.presenter.BasePresenter;
import com.zkp.gank.bean.ProjectTreeBean;
import com.zkp.gank.http.ApiService;
import com.zkp.gank.http.AppConfig;
import com.zkp.gank.http.HttpUtil;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

/**
 * @author: zkp
 * @project: Gank
 * @package: com.zkp.gank.module.project
 * @time: 2019/4/30 10:28
 * @description:
 */
public class ProjectPresenter extends BasePresenter<ProjectFragmentContract.View> implements ProjectFragmentContract.Presenter {

    @Inject
    ProjectPresenter() {
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getProjectTree() {
        if (mView != null) {
            mView.showLoading();

            HttpUtil.request(HttpUtil.createApi(GankApplication.getContext(), AppConfig.BASE_URL, ApiService.class).getProjectTree(), new HttpUtil.IResponseListener<ProjectTreeBean>() {
                @Override
                public void onSuccess(ProjectTreeBean data) {
                    if (data.getErrorCode() == 0) {
                        mView.getProjectTreeSuccess(data);
                    } else {
                        mView.getProjectTreeError(data.getErrorMsg());
                    }
                    mView.hideLoading();
                }

                @Override
                public void onFail(String errMsg) {
                    mView.getProjectTreeError(errMsg);
                    mView.hideLoading();
                }
            });
        }
    }
}
