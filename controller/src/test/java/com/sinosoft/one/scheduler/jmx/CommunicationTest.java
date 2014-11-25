package com.sinosoft.one.scheduler.jmx;

import org.one.schedule.jmx.Communication;
import org.one.schedule.model.App;
import org.one.schedule.model.Job;
import org.one.schedule.model.Trigger;
import org.one.schedule.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bin on 14-6-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml"})
public class CommunicationTest {

    @Autowired
    private Communication communication;
    @Autowired
    private AppService appService;

    @Test
    public void saveTest(){
        Trigger trigger = new Trigger();
        trigger.setName("trigger1");
        trigger.setAppId("4028802946a2af970146a2b0143d0000");
        trigger.setCronExpression("0 0/1 * * * ?");
        Job job = new Job();
        job.setName("a1");
        trigger.setJob(job);
//        try {
//            communication.save(trigger);
//        } catch (ReflectionException e) {
//            e.printStackTrace();
//        } catch (MBeanException e) {
//            e.printStackTrace();
//        } catch (InstanceNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void deleteTest(){
        Trigger trigger = new Trigger();
        trigger.setName("trigger1");
        trigger.setAppId("4028802946a2af970146a2b0143d0000");
//        try {
//            communication.delete(trigger);
//        } catch (ReflectionException e) {
//            e.printStackTrace();
//        } catch (MBeanException e) {
//            e.printStackTrace();
//        } catch (InstanceNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void pauseTest(){
        Trigger trigger = new Trigger();
        trigger.setName("trigger1");
        trigger.setAppId("4028802946a2af970146a2b0143d0000");
//        try {
//            communication.pauseTrigger(trigger);
//        } catch (ReflectionException e) {
//            e.printStackTrace();
//        } catch (MBeanException e) {
//            e.printStackTrace();
//        } catch (InstanceNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void resumeTest(){
        Trigger trigger = new Trigger();
        trigger.setName("trigger1");
        trigger.setAppId("4028802946a2af970146a2b0143d0000");
//        try {
//            communication.resumeTrigger(trigger);
//        } catch (ReflectionException e) {
//            e.printStackTrace();
//        } catch (MBeanException e) {
//            e.printStackTrace();
//        } catch (InstanceNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void triggerJobTest(){
        App app = new App();
        app.setName("app1");
        app.setIp("127.0.0.1");
        app.setPort(new BigDecimal(8801));
        Job job  = new Job();
        job.setName("a1");
//        try {
//            communication.triggerJob(job);
//        } catch (ReflectionException e) {
//            e.printStackTrace();
//        } catch (MBeanException e) {
//            e.printStackTrace();
//        } catch (InstanceNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void getAllJob(){
        List<App> apps = appService.listByIdAndFlag();
        for(App app:apps){
            List<Job> jobs =  communication.getAllJobDetails(app);
            for(Job job:jobs){
                System.out.println(job.getName());
            }
        }

    }
}
