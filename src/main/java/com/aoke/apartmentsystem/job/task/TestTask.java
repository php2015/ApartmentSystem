package com.aoke.apartmentsystem.job.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiaoxinglin
 */
@Slf4j
@Component
public class TestTask {

    Logger log = LoggerFactory.getLogger(TestTask.class);

    public void test(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }
    public void test1() {
        log.info("我是不带参数的test1方法，正在被执行");
    }

}
