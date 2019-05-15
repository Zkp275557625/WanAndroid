package com.zkp.gank.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatDelegate;

import com.baidu.mapapi.SDKInitializer;
import com.coder.zzq.smartshow.core.SmartShow;
import com.zkp.gank.base.activity.BaseActivity;
import com.zkp.gank.base.fragment.BaseFragment;
import com.zkp.gank.crash.UnCaughtHandler;
import com.zkp.gank.db.greendao.DaoMaster;
import com.zkp.gank.db.greendao.DaoSession;
import com.zkp.gank.http.AppConfig;
import com.zkp.gank.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: zkp
 * @project: Gank
 * @package: com.zkp.gank.app
 * @time: 2019/4/10 16:42
 * @description:
 */
public class GankApplication extends Application {


    private static Context mContext;
    private static GankApplication mApplication;
    private static DaoSession daoSession;
    private List<BaseActivity> mActivityList;
    private List<BaseFragment> mFragmentsList;

    public static GankApplication getApplication() {
        return mApplication;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mApplication = this;

        mActivityList = new ArrayList<>();
        mFragmentsList = new ArrayList<>();

        //初始化SmartShow
        SmartShow.init(this);
        SDKInitializer.initialize(this);

        if (SPUtils.getBoolean(this, "isNightMode")) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }

        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(GankApplication.getContext(), AppConfig.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 闪退重启
     */
    public void initUnCaughtHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtHandler(this));
    }

    public void addActivity(BaseActivity activity) {
        mActivityList.add(activity);
    }

    public void addFragment(BaseFragment fragment) {
        mFragmentsList.add(fragment);
    }

    /**
     * 退出应用
     */
    public void exitApplication() {
        for (BaseActivity activity : mActivityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        for (BaseFragment fragment : mFragmentsList) {
            if (fragment != null) {
                Objects.requireNonNull(fragment.getActivity()).onBackPressed();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
