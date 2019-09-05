package com.aoke.apartmentsystem.third.handler;

import com.alibaba.fastjson.JSONObject;
import com.aoke.apartmentsystem.third.BaseHandler;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class LoginHandler extends BaseHandler {

    Logger log = LoggerFactory.getLogger(LoginHandler.class);

    public static JSONObject jsonObject;

    public static JSONObject getInstance(){
        if(jsonObject == null){
            jsonObject = new JSONObject();
        }
        return jsonObject;
    }

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());//用数组实现的有界阻塞队列，必须设置容量

    @Override
    public void run() {
        pool.execute(new BaseHandler());
    }


}
