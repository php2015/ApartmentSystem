package com.aoke.apartmentsystem.scheduledtask;

import com.alibaba.fastjson.JSONObject;
import com.aoke.apartmentsystem.common.utils.DESEncrypt;
import com.aoke.apartmentsystem.common.utils.HttpClient;
import com.aoke.apartmentsystem.third.constant.AccessToken;
import com.aoke.apartmentsystem.third.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableScheduling
@Slf4j
@Component
public class ScheduledTask {

    Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    //@Scheduled(cron="0 0/2 * * * ?")//
    @Scheduled(cron="0 0 6,13,20 * * ?")//每天上午6点，下午1点，8点
    public void getAccessToken() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("account",Constant.OPS_HOST);
        DESEncrypt desPlus2 = new DESEncrypt(Constant.HH_KEY);
        String e2 = desPlus2.encrypt(Constant.PASSWORD);
        paramMap.put("password",e2);
        String res = HttpClient.post(Constant.BASE_URL+Constant.ApiAddress.LOGIN, JSONObject.toJSONString(paramMap), null, UUID.randomUUID().toString());
        log.info("res:"+res);
        AccessToken.access_token = JSONObject.parseObject(JSONObject.parseObject(res).getString("data")).getString("access_token");
        log.info("每7小时执行一次access_token:"+AccessToken.access_token);
        System.out.println(res);
    }

//    @Scheduled(cron="0 0/3 * * * ?")
//    public void testTwo() {
//        Map<String, Object> paramMapLock = new HashMap<String, Object>();
//        paramMapLock.put("page_size",10);
//        paramMapLock.put("current_page",1);
//        paramMapLock.put("node_no",null);
//        paramMapLock.put("lock_no",null);
//        paramMapLock.put("house_code",null);
//        paramMapLock.put("room_code",null);
//        String resLock = HttpClient.post(Constant.BASE_URL+Constant.ApiAddress.LOCK_LIST,JSONObject.toJSONString(paramMapLock), AccessToken.access_token,UUID.randomUUID().toString());
//        System.out.println(resLock);
//        log.info("每3分钟执行一次");
//    }
}
