package com.youngor.wsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Auther zhoutf
 * @Date 2019/2/14 14:55
 * @Description 定时器配置
 */
@Configuration
public class SchedledConfiguration {

    @Bean(name = "detailFactoryBean")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTasks scheduledTasks) {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        //这儿设置对应的Job对象
        bean.setTargetObject(scheduledTasks);
        //这儿设置对应的方法名  与执行具体任务调度类中的方法名对应
        bean.setTargetMethod("work");
        bean.setConcurrent(false);
        return bean;
    }

    @Bean(name = "cronTriggerBean")
    public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean detailFactoryBean) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(detailFactoryBean.getObject());
        try {
            //trigger.setCronExpression ("00 00 00 * * ?");//每天00：00：00执行一次
            //  trigger.setCronExpression ("00 00 01 * * ?");//每天01：00：00执行一次
              trigger.setCronExpression("0/10 * * * * ? ");//每10秒执行一次
            //  trigger.setCronExpression("* 0/10 * * * ? ");  //每10分钟执行一次
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return trigger;

    }
    @Bean
    public SchedulerFactoryBean schedulerFactory(CronTriggerFactoryBean cronTriggerBean) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(cronTriggerBean.getObject());
        return schedulerFactory;
    }

}
