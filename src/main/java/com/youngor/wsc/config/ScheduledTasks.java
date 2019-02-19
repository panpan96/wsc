package com.youngor.wsc.config;



import com.youngor.wsc.common.DateFormatUtils;
import com.youngor.wsc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther zhoutf
 * @Date 2019/2/14 14:55
 * @Description 定时任务
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

   // @Autowired
    //private ItemService itemService;

    public void work() {


        System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd  hh:mm:ss"));
       // itemService.saveItemMessage();

    }
}
