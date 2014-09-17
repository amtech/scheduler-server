package com.sinosoft.one.schedule.controllers.app;

import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Param;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Get;
import com.sinosoft.one.mvc.web.annotation.rest.Post;
import com.sinosoft.one.mvc.web.instruction.reply.Reply;
import com.sinosoft.one.mvc.web.instruction.reply.Replys;
import com.sinosoft.one.mvc.web.instruction.reply.transport.Json;
import com.sinosoft.one.schedule.model.App;
import com.sinosoft.one.schedule.model.Job;
import com.sinosoft.one.schedule.model.Log;
import com.sinosoft.one.schedule.model.Trigger;
import com.sinosoft.one.schedule.service.AppService;
import com.sinosoft.one.schedule.service.JobService;
import com.sinosoft.one.schedule.service.LogService;
import com.sinosoft.one.schedule.service.TriggerService;
import com.sinosoft.one.schedule.vo.TriggerInfo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("")
public class AppController {

	@Autowired
	private AppService appService;
	
	@Autowired
	private JobService jobService;

	@Autowired 
	private TriggerService triggerService;
	
	@Autowired 
	private LogService logService;
    @Autowired
	public CacheManager cacheManager;

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Get("list")
	public String appList(Invocation inv) {
		List<App> apps = appService.list();
		inv.addModel("apps", apps);
		return "applist";
	}
	
	@Post("listJobByAppId")
	public String listJobByAppId(@Param("appId")String appId,@Param("appName")String appName,Invocation inv) {
		List<Job> jobs = jobService.listByAppId(appId);
		inv.addModel("jobs", jobs);
		inv.addModel("appname", appName);
		inv.addModel("appid", appId);
		return "applist_joblist";
	}
	
	@Get("listTriggerByAppidJobId/{jobid}/{appid}")
	public String listTriggerByAppidJobId(@Param("jobid") String jobid,
			@Param("appid") String appid, Invocation inv) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Job job = jobService.getJob(jobid);
		List<Trigger> triggers = triggerService.listByAppidJobId(appid, jobid);
		List<String> triggerids = new ArrayList<String>();
		for (Trigger trigger : triggers) {
			triggerids.add(trigger.getId());
		}
		List<Log> logs = logService.findLogsByAppidJobID(appid, jobid,
				triggerids);
		List<TriggerInfo> triggerInfos = new ArrayList<TriggerInfo>();
		for (Trigger trigger : triggers) {
			TriggerInfo triggerInfo = new TriggerInfo();
			triggerInfo.setTriggerid(trigger.getId());
			triggerInfo.setAppId(appid);
			triggerInfo.setName(trigger.getName());
			triggerInfo.setCronExpression(trigger.getCronExpression());
			triggerInfo.setRecordStatus(trigger.getRecordStatus());
			triggerInfo.setStatus(trigger.getStatus());
			for (int i=0;i<logs.size();i++) {
				if (logs.get(i).getTriggerId().equals(trigger.getId())) {
                    Date lastStartTime = logs.get(i).getBeginTime();
                    triggerInfo.setLastStartTime(sdf.format(lastStartTime));
                    CronExpression cronExpression = null;
                    try {
                        cronExpression = new CronExpression(trigger.getCronExpression());
                    } catch (ParseException e) {
                        logger.error(e.getMessage());
                    }
                    Date nextStartTime = cronExpression.getNextValidTimeAfter(lastStartTime);
                    triggerInfo.setNextStartTime(sdf.format(nextStartTime));
                    break;
				}
			}
			triggerInfos.add(triggerInfo);
		}
		inv.addModel("triggerInfos", triggerInfos);
		inv.addModel("jobname", job.getName());
		inv.addModel("jobId", jobid);
		return "applist_triggerinfo";
	}
	
	@Post("check/{appid}")
	public Reply check(@Param("appid") String appid, Invocation inv) throws IOException{
		String status =appService.checkStatus(appid);
		if(status=="0"){
			inv.getResponse().getWriter().write("false");
			return null;
		}
		inv.getResponse().setContentType("text/html;charset=UTF-8");
		inv.getResponse().getWriter().write("true");;
		return null;
	}
	
	@Post("save")
	public Reply save(@Param("name") String name,@Param("ip") String ip, @Param("port") String port){
		App app=new App();
		app.setName(name);
		app.setIp(ip);
		app.setPort(new BigDecimal(Integer.parseInt(port)));
		Map<String, String> msg=new HashMap<String, String>();
		try {
			msg=appService.save(app);
            //代表保存成功
            if (cacheManager.getCache("appCache") == null) {
                Cache cache = new Cache("appCache", 100, true, false, 0, 0);
                cacheManager.addCache(cache);
            }
            Map<String, Object>cacheMap=new HashMap<String, Object>();
            cacheMap.put("name", name);
            cacheMap.put("date", new Date());
            Element element = new Element(msg.get("appName"), cacheMap);
            cacheManager.getCache("appCache").put(element);

		} catch (Exception e) {
			msg.put("statusCode", "503");
			e.printStackTrace();
		}finally{
			return Replys.with(msg).as(Json.class);
		}
	}
	
	@Post("heartBeat")
	public  Reply heartBeat(@Param("appName") String appName){
		Map<String, String> massege=new HashMap<String, String>();
		if(cacheManager.getCache("appCache") == null){
			Cache cache = new Cache("appCache", 100, true, false, 0, 0);
			cacheManager.addCache(cache);
		}
		if(cacheManager.getCache("appCache").get(appName)!=null){
			Element element =  cacheManager.getCache("appCache").get(appName);
			Map<String, Object>cacheMap=(Map<String, Object>) element.getValue();
			if(cacheMap.get("name").equals(appName)){
				cacheMap.put("date", Calendar.getInstance().getTime());
				Element newElement = new Element(appName, cacheMap);
				cacheManager.getCache("appCache").put(newElement);
			}
		}
		return Replys.with(massege).as(Json.class);
	}
}
