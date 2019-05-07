package com.zkp.gank.http;

import android.os.Environment;

import java.io.File;

/**
 * @author: zkp
 * @project: TianGuaTrip
 * @package: com.ot.tgtrip.http
 * @time: 2019/4/9 14:47
 * @description: App配置类
 */
public class AppConfig {

    public static final String APP_PATH = Environment.getExternalStorageDirectory() + File.separator + "玩安卓";

    public static final String SHARED_PREFERENCES_NAME = "gankAPP";

    /**
     * 读取超时 默认设置为10s
     */
    public static final int TIMEOUT_READ = 10;
    /**
     * 连接超时 默认设置为10s
     */
    public static final int TIMEOUT_CONNECTION = 10;

    public static final String BASE_URL = "https://www.wanandroid.com";

    public static final String URL_UPDATE = "http://mock-api.com/3EgdX1gM.mock/getUpdateInfo";

    public static final String CURRENT_FRAGMENT_KEY = "current_fragment";

    /**
     * 首页
     */
    public static final int TYPE_HOME_PAGER = 0;
    /**
     * 知识体系
     */
    public static final int TYPE_KNOWLEDGE = 1;
    /**
     * 微信公众号
     */
    public static final int TYPE_WX_ARTICLE = 2;
    /**
     * 导航
     */
    public static final int TYPE_NAVIGATION = 3;
    /**
     * 项目
     */
    public static final int TYPE_PROJECT = 4;
    /**
     * 我的收藏
     */
    public static final int TYPE_COLLECT = 5;
    /**
     * 设置
     */
    public static final int TYPE_SETTING = 8;
    /**
     * 关于
     */
    public static final int TYPE_ABOUT_US = 9;
    /**
     * 常用网站
     */
    public static final int TYPE_USEFUL_SITES = 10;
    /**
     * 搜索结果
     */
    public static final int TYPE_SEARCH_RESULT = 11;

    /**
     * TODO分类
     */
    public static final int TODO_TYPE_ALL = 0;
    public static final int TODO_TYPE_WORK = 1;
    public static final int TODO_TYPE_STUDY = 2;
    public static final int TODO_TYPE_LIFE = 3;
    public static final int TODO_TYPE_OTHER = 4;

    public static final int TODO_PRIORITY_FIRST = 1;
    public static final int TODO_PRIORITY_SECOND = 2;

    /**
     * TODO列表参数
     */
    public static final String KEY_TODO_TITLE = "title";
    public static final String KEY_TODO_CONTENT = "content";
    public static final String KEY_TODO_DATE = "date";
    public static final String KEY_TODO_TYPE = "type";
    public static final String KEY_TODO_STATUS = "status";
    public static final String KEY_TODO_PRIORITY = "priority";
    public static final String KEY_TODO_ORDERBY = "orderby";

    /**
     * 连续两次点击时间间隔<2000,则退出应用
     */
    public static final long EXIT_TIME = 2000;


    /**
     * About Url
     */
    public static final String ABOUT_WEBSITE = "https://www.wanandroid.com/about";
    public static final String ABOUT_SOURCE_CODE = "https://github.com/Zkp275557625/Gank";
    public static final String ABOUT_FEEDBACK = "https://github.com/Zkp275557625/Gank/issues";
}
