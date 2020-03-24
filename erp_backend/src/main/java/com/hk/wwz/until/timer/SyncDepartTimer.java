package com.hk.wwz.until.timer;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.SyncDataService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
@EnableScheduling
@Configuration
public class SyncDepartTimer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    SyncDataService syncDataService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void syncDepartData() {
        Date date = new Date(System.currentTimeMillis());
        logger.info("开始定时任务,时间是：" + date.toLocaleString());
        try {
            ResultBean resultBean = syncDataService.syncInfo();
            logger.info("同步结果是：" + JSON.toJSONString(resultBean));
        } catch (ApiException e) {
            logger.error("执行任务失败，原因是：" + e);
        }
    }

    @PostConstruct
    public void runSync() {
        syncDepartData();
    }
}
