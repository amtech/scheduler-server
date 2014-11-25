package org.one.schedule.service;

import org.one.schedule.repository.AppRepository;
import org.one.schedule.repository.JobRepository;
import org.one.schedule.repository.LogRepository;
import org.one.schedule.repository.TriggerRepository;
import org.one.schedule.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private AppRepository appRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private TriggerRepository triggerRepository;

	public List<Log> findLogsByAppidJobID(String appid, String jobid,
			List<String> triggerids) {
		if (triggerids.size() > 0) {
			return logRepository.findLogsByAppidJobID(appid, jobid, triggerids);
		}
		triggerids.add(" ");
		return logRepository.findLogsByAppidJobID(appid, jobid, triggerids);
	}

	/**
	 * 日志查询
	 * 
	 * @param appid
	 * @param jobid
	 * @param triggerid
	 * @param status
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public List<LogInfo> listLogInfo(String appid, String jobid,
			String triggerid, String status, String begintime, String endtime) {

		if (appid == null) {
			appid = "";
		}
		if (jobid == null) {
			jobid = "";
		}
		if (triggerid == null) {
			triggerid = "";
		}
		if (status == null) {
			status = "";
		}
		if (begintime != null && begintime.equals("")) {
			begintime = null;
		}
		if (endtime != null && endtime.equals("")) {
            endtime = null;
		}
		if (begintime == null && endtime == null) {
			return logRepository.listLogInfo(appid, jobid, triggerid, status);
		}
		if (begintime == null && endtime !=null) {
			return logRepository.listLogInfo_begintime_end(appid, jobid, triggerid,
					status, endtime, "yyyy-MM-dd hh24:mi:ss");
		}
		if (endtime == null && begintime != null ) {
			return logRepository.listLogInfo_begintime(appid, jobid, triggerid,
					status, begintime, "yyyy-MM-dd hh24:mi:ss");
		}
		return logRepository.listLogInfo(appid, jobid, triggerid, status,
				begintime, endtime, "yyyy-MM-dd hh24:mi:ss");
	}

	/**
	 * 日志查询
	 * 
	 * @param appid
	 * @param jobid
	 * @param begintime
	 * @return
	 */
	public List<LogStaticInfo> listLogStaticInfo(String appid, String jobid,
			String begintime, String begintime_end) {

		if (appid == null) {
			appid = "";
		}
		if (jobid == null) {
			jobid = "";
		}

		if (begintime != null && begintime.equals("")) {
			begintime = null;
		}
		if (begintime_end != null && begintime_end.equals("")) {
			begintime_end = null;
		}
		if (begintime == null && begintime_end == null) {
			return logRepository.listStaticInfo(appid, jobid);
		}
		if (begintime == null && begintime_end !=null ) {
			return logRepository.listStaticInfo_begintime(appid, jobid, begintime, "yyyy-MM-dd hh24:mi:ss");
		}
		if (begintime_end == null && begintime != null) {
			return logRepository.listStaticInfo_begintime_end(appid, jobid, begintime_end, "yyyy-MM-dd hh24:mi:ss");
		}
		return logRepository.listStaticInfo(appid, jobid, begintime, begintime_end,  "yyyy-MM-dd hh24:mi:ss");
	}

	public Map<String, String> save(String appName, String id, String jobName,
			String beginTime, String endTime, String triggerName,
			String exceptionMsg) throws ParseException,
			Exception {

		Map<String, String> message = new HashMap<String, String>();
        Log log = new Log();
		App app = appRepository.findAppByName(appName);
		if (app == null) {
			message.put("statusCode", "500");
			message.put("errorMsg", "app不存在");
			return message;
		}
		Job job = jobRepository.findByName(jobName);
		if (job == null) {
			message.put("statusCode", "500");
			message.put("errorMsg", "job不存在");
			return message;
		}
		Trigger trigger = triggerRepository.findByName(triggerName);
//		if (trigger == null){
//			message.put("statusCode", "500");
//			message.put("errorMsg", "trigger不存在");
//			return message;
//            //log.setTriggerId("临时计划");
//		}
        if(trigger!=null){
            log.setTriggerId(trigger.getId());
        }
        log.setAppId(app.getId());
        log.setJobId(job.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.setBeginTime(sdf.parse(beginTime));
        if (exceptionMsg != null && !exceptionMsg.equals("")) {
            log.setExceptionMsg(exceptionMsg);
            log.setSendStatus("0");
            log.setStatus("0");
        }else {
            log.setStatus("1");
        }
		if (id != null && !id.equals("")) {
            log.setId(id);
			log.setEndTime(sdf.parse(endTime));
            log.setTimeConsuming(new BigDecimal((sdf.parse(endTime).getTime()-sdf.parse(beginTime).getTime())/1000));
		}
		Log newlog = logRepository.save(log);
		message.put("statusCode", "200");
		message.put("id", newlog.getId());
		return message;
	}
	
	public Log findLogBytriggerIdRecentTime(String triggerid){
		return logRepository.findLogBytriggerIdRecentTime(triggerid);
	}

    public Log findOne(String logId) {
        return logRepository.findOne(logId);
    }
}
