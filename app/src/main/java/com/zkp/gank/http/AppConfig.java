package com.zkp.gank.http;

/**
 * @author: zkp
 * @project: TianGuaTrip
 * @package: com.ot.tgtrip.http
 * @time: 2019/4/9 14:47
 * @description: App配置类
 */
public class AppConfig {
    /**
     * 读取超时 默认设置为10s
     */
    public static final int TIMEOUT_READ = 10;
    /**
     * 连接超时 默认设置为10s
     */
    public static final int TIMEOUT_CONNECTION = 10;

    public static final String BASE_URL = "https://www.wanandroid.com";

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
     * 连续两次点击时间间隔<2000,则退出应用
     */
    public static final long EXIT_TIME = 2000;
}
