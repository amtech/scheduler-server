package com.sinosoft.one.schedule.service;

import com.sinosoft.one.schedule.model.App;
import com.sinosoft.one.schedule.model.Contacts;
import com.sinosoft.one.schedule.model.Log;
import com.sinosoft.one.schedule.repository.AppRepository;
import com.sinosoft.one.schedule.repository.JobRepository;
import com.sinosoft.one.schedule.repository.TriggerRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by bin on 14-7-3.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TriggerRepository triggerRepository;

    public void sendMail(String[] fileNames){

        for(int i=0;i<fileNames.length;i++){
            String fileName = fileNames[i].split(".")[0];
            App app = appRepository.findAppByName(fileName);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
                helper.setSubject("出现异常的任务");
                List<Contacts> contactses = app.getContactses();
                String[] receiver = new String[contactses.size()];
                for(int j=0;i<contactses.size();i++){
                    receiver[j] = contactses.get(j).getMail();
                }
                helper.setTo(receiver);
                helper.setText("异常数据见附件",true);
                File file = new File(this.getClass().getResource("/").getPath()+fileName);
                FileSystemResource attachment=new FileSystemResource(file);
                helper.addAttachment("附件",attachment);
                mailSender.send(mimeMessage);
                //file.delete();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }

    public void createAttachment(Map<String, List<Log>> map) {

        Iterator<String> iterator = map.keySet().iterator();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (iterator.hasNext()){
            String appId = iterator.next();
            String appName = appRepository.findOne(appId).getName();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("异常数据");
            HSSFRow title = sheet.createRow(0);
            HSSFCell app = title.createCell(0);
            app.setCellValue("应用名");
            HSSFCell job = title.createCell(1);
            job.setCellValue("任务名");
            HSSFCell trigger = title.createCell(2);
            trigger.setCellValue("计划名");
            HSSFCell beginTime = title.createCell(3);
            beginTime.setCellValue("开始时间");
            HSSFCell endTime = title.createCell(4);
            endTime.setCellValue("结束时间");
            HSSFCell timeConsuming = title.createCell(5);
            timeConsuming.setCellValue("耗时");
            HSSFCell status = title.createCell(6);
            status.setCellValue("状态");
//            HSSFCell exceptionMsg = title.createCell(7);
//            exceptionMsg.setCellValue("异常信息");
            for(int i=0;i<map.get(appId).size();i++){
                Log log = map.get(appId).get(i);
                HSSFRow row = sheet.createRow(i+1);
                HSSFCell appValue = row.createCell(0);
                appValue.setCellValue(appName);
                HSSFCell jobValue = row.createCell(1);
                jobValue.setCellValue(jobRepository.findOne(log.getJobId()).getName());
                HSSFCell triggerValue = row.createCell(2);
                triggerValue.setCellValue(triggerRepository.findOne(log.getTriggerId()).getName());
                HSSFCell beginTimeValue = row.createCell(3);
                beginTimeValue.setCellValue(sdf.format(log.getBeginTime()));
                HSSFCell endTimeValue = row.createCell(4);
                endTimeValue.setCellValue(sdf.format(log.getEndTime()));
                HSSFCell timeConsumingValue = row.createCell(5);
                timeConsumingValue.setCellValue(log.getTimeConsuming().doubleValue());
                HSSFCell statusValue = row.createCell(6);
                if(map.get(appId).get(i).getStatus().equals("0")){
                    statusValue.setCellValue("失败");
                }
//                HSSFCell exceptionMsgValue = row.createCell(7);
//                exceptionMsgValue.setCellValue(log.getExceptionMsg());
            }
            File file = new File(this.getClass().getResource("/").getPath()+appName+sdf.format(new Date())+".xls");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}
