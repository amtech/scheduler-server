package com.sinosoft.one.scheduler.service;

import com.sinosoft.one.schedule.model.Log;
import com.sinosoft.one.schedule.repository.LogRepository;
import com.sinosoft.one.schedule.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by bin on 14-7-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml"})
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private LogRepository logRepository;

    @Test
    public void sendMailTest(){
//        Properties props = System.getProperties();
//        props.setProperty("http.proxyHost","172.16.251.58");
//        props.setProperty("http.proxyPort","3128");
    }

    @Test
    public void createAttachmentTest(){
        List<Log> logList = logRepository.findLogBySendStatus();
        Map<String,List<Log>> map = new HashMap<String, List<Log>>();
        for(Log log:logList){
            if(map.get(log.getAppId())!=null){
                map.get(log.getAppId()).add(log);
            }else{
                List<Log> logs = new ArrayList<Log>();
                logs.add(log);
                map.put(log.getAppId(),logs);
            }
        }
        mailService.createAttachment(map);
    }
}
