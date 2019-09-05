package com.aoke.apartmentsystem.third.constant;

/**
 * 第三方(火河)接口常量
 */
public interface Constant {

    /**
     * 火河
     */
    static final String HH = "HH";
    /**
     * 接口URL
     */
    static final String BASE_URL = "http://ops.huohetech.com";

    /**
     * 端口
     */
    static final String PORT = "80";
    /**
     * LM管理账号
     */
    static final String LM_HOST = "18649619459";
    /**
     * OPS接口账号
     */
    //static final String OPS_HOST = "18259062529";
    static final String OPS_HOST = "18030158722";
    /**
     * 密码
     */
    //static final String PASSWORD = "EA888888";
    static final String PASSWORD = "kittys88";
    /**
     * 密钥
     */
    static final String HH_KEY = "ozXzd0v1";
    /**
     * 运营商编码
     */
    static final String OPERATOR_CODE = "1521";
    /**
     * 加密方式
     */
    static final String ENCRYPTION = "DES";
    /**
     * get
     */
    static final String REQUEST_METHOD_GET = "GET";
    /**
     * post
     */
    static final String REQUEST_METHOD_POST = "POST";

    public interface ApiAddress {
        /**
         * 登录
         */
        static final String LOGIN = "/login";

        /**
         * 3.1. 查询网关列表
         */
        static final String NODE_LIST = "/node/list";

        /**
         * 3.3. 查询门锁列表
         */
        static final String LOCK_LIST = "/lock/list";

        /**
         * 3.4. 查询门锁详情
         */
        static final String LOCK_VIEW = "/lock/view";
    }
}
