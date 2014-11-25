package org.one.schedule.controllers.quartztask;

import org.one.schedule.model.Log;
import org.one.schedule.repository.LogRepository;
import org.one.schedule.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bin on 14-7-17.
 */
@Component
public class MailTask {

    @Autowired
    private MailService mailService;
    @Autowired
    private LogRepository logRepository;

    public void sendMail(){

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

        String path = this.getClass().getResource("/").getPath();
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".xls")){
                    return  true;
                }
                return false;
            }
        };
        File file = new File(path);
        String[] fileNames = file.list(filenameFilter);

        mailService.sendMail(fileNames);

        for(Log log:logList){
            log.setSendStatus("1");
        }

        logRepository.save(logList);

    }


}
