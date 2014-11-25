package org.one.schedule.controllers.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.one.schedule.model.*;
import org.one.schedule.vo.LogQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Param;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Get;
import com.sinosoft.one.mvc.web.annotation.rest.Post;
import com.sinosoft.one.mvc.web.instruction.reply.Reply;
import com.sinosoft.one.mvc.web.instruction.reply.Replys;
import com.sinosoft.one.mvc.web.instruction.reply.transport.Json;
import com.sinosoft.one.mvc.web.instruction.reply.transport.Raw;
import org.one.schedule.service.AppService;
import org.one.schedule.service.JobService;
import org.one.schedule.service.LogService;
import org.one.schedule.service.TriggerService;

@Path("")
public class LogController {

	@Autowired
	public LogService logService;

	@Autowired
	public AppService appService;

	@Autowired
	public JobService jobService;

	@Autowired
	public TriggerService triggerService;

    @Autowired
	public CacheManager cacheManager;

	@Get("view")
	public String view(Invocation inv) {
		return "list";
	}

	@Post("list")
	public String list(LogQueryCondition logQueryCondition, Invocation inv) {
		List<LogInfo> logInfos = null;
		if (cacheManager.getCache("logCache") == null) {
			Cache cache = new Cache("logCache", 100, true, false, 20, 20);
			cacheManager.addCache(cache);
		}
		if (cacheManager.getCache("logCache").get(logQueryCondition.toString()) != null) {
			logInfos =  (List<LogInfo>)cacheManager.getCache("logCache")
					.get(logQueryCondition.toString()).getValue();
            for(LogInfo logInfo:logInfos){
                if(logInfo.getTriggerName()==null){
                    logInfo.setTriggerName("临时任务");
                }
            }
			inv.addModel("logs", logInfos);
			inv.addModel("logQueryCondition", logQueryCondition.toString());
			return "list_table";
		}
		logInfos = logService.listLogInfo(logQueryCondition.getAppId(),
				logQueryCondition.getJobId(), logQueryCondition.getTriggerId(),
				logQueryCondition.getStatus(),
				logQueryCondition.getBeginTime(),
				logQueryCondition.getEndTime());
        for(LogInfo logInfo:logInfos){
            if(logInfo.getTriggerName()==null){
                logInfo.setTriggerName("临时任务");
            }
        }
		Element element = new Element(logQueryCondition.toString(), logInfos);
		cacheManager.getCache("logCache").put(element);
		inv.addModel("logs", logInfos);
		inv.addModel("logQueryCondition", logQueryCondition.toString());
		return "list_table";
	}

	@Post("exportExcel")
	public Reply exportExcel(LogQueryCondition logQueryCondition, Invocation inv)
			throws IOException {
		Collection<LogInfo> logInfos = null;

		if (cacheManager.getCache("logCache") == null) {
			Cache cache = new Cache("logCache", 100, true, false, 20, 20);
			cacheManager.addCache(cache);
		}
		if (cacheManager.getCache("logCache").get(logQueryCondition.toString()) != null) {
			logInfos = (Collection<LogInfo>) cacheManager.getCache("logCache")
					.get(logQueryCondition.toString()).getValue();
			return Replys.with(logQueryCondition.toString());
		}
		logInfos = logService.listLogInfo(logQueryCondition.getAppId(),
				logQueryCondition.getJobId(), logQueryCondition.getTriggerId(),
				logQueryCondition.getStatus(),
				logQueryCondition.getBeginTime(),
				logQueryCondition.getEndTime());
		Element element = new Element(logQueryCondition.toString(), logInfos);
		cacheManager.getCache("logCache").put(element);
		inv.getResponse().setContentType("text/html;charset=UTF-8");
		inv.getResponse().getWriter().write(logQueryCondition.toString());
		return null;
	}

	@Get("exportExcel/{logQueryCondition}")
	public Reply exportExcel(
			@Param("logQueryCondition") String logQueryCondition, Invocation inv) {
		if (cacheManager.getCache("logCache") == null) {
			Cache cache = new Cache("logCache", 100, true, false, 20, 20);
			cacheManager.addCache(cache);
		}
		String fileName = "日志列表.xls";
		if (cacheManager.getCache("logCache").get(logQueryCondition.toString()) != null) {
			Collection<LogInfo> logInfos = (Collection<LogInfo>) cacheManager
					.getCache("logCache").get(logQueryCondition.toString())
					.getValue();
			LogExcel<LogInfo> logExcel = new LogExcel<LogInfo>(logInfos,
					"LogInfo", fileName);
			return Replys
					.with(((ByteArrayOutputStream) logExcel.getOut())
							.toByteArray()).as(Raw.class)
					.downloadFileName(fileName);
		}
		LogQueryCondition queryCondition = JSON.parseObject(logQueryCondition,
				LogQueryCondition.class);
		Collection<LogInfo> logInfos = logService.listLogInfo(
				queryCondition.getAppId(), queryCondition.getJobId(),
				queryCondition.getTriggerId(), queryCondition.getStatus(),
				queryCondition.getBeginTime(), queryCondition.getEndTime());
		Element element = new Element(queryCondition.toString(), logInfos);
		cacheManager.getCache("logCache").put(element);
		LogExcel<LogInfo> logExcel = new LogExcel<LogInfo>(logInfos,
				"LogInfo", fileName);
		return Replys
				.with(((ByteArrayOutputStream) logExcel.getOut()).toByteArray())
				.as(Raw.class).downloadFileName(fileName);

	}

	/**
	 * 日志统计页面跳转
	 * 
	 * @param inv
	 * @return
	 */
	@Get("statistics")
	public String statistics(Invocation inv) {
		return "statistics";
	}

	/**
	 * 日志统计查询
	 * 
	 * @param logQueryCondition
	 * @param inv
	 * @return
	 */
	@Post("queryStatistics")
	public String queryStatistics(LogQueryCondition logQueryCondition,
			Invocation inv) {
		Collection<LogStaticInfo> logInfos = null;
		if (cacheManager.getCache("logStaticCache") == null) {
			Cache cache = new Cache("logStaticCache", 100, true, false, 20, 20);
			cacheManager.addCache(cache);
		}
		if (cacheManager.getCache("logStaticCache").get(logQueryCondition.toString()) != null) {
			logInfos = (Collection<LogStaticInfo>) cacheManager.getCache("logStaticCache")
					.get(logQueryCondition.toString()).getValue();
			inv.addModel("logStatics", logInfos);
			inv.addModel("logQueryCondition", logQueryCondition.toString());
			return "statistics_table";
		}
		logInfos = logService.listLogStaticInfo(logQueryCondition.getAppId(),
				logQueryCondition.getJobId(), logQueryCondition.getBeginTime(),
				logQueryCondition.getEndTime());
		Element element = new Element(logQueryCondition.toString(), logInfos);
		cacheManager.getCache("logStaticCache").put(element);
		inv.addModel("logStatics", logInfos);
		inv.addModel("logQueryCondition", logQueryCondition.toString());
		return "statistics_table";
	}

	
	@Post("statisticsExportExcel")
	public Reply statisticsExportExcel(LogQueryCondition logQueryCondition, Invocation inv)
			throws IOException {
		Collection<LogStaticInfo> logInfos = null;

		if (cacheManager.getCache("logStaticCache") == null) {
			Cache cache = new Cache("logStaticCache", 100, true, false, 20, 20);
			cacheManager.addCache(cache);
		}
		if (cacheManager.getCache("logStaticCache").get(logQueryCondition.toString()) != null) {
			logInfos = (Collection<LogStaticInfo>) cacheManager.getCache("logStaticCache")
					.get(logQueryCondition.toString()).getValue();
			return Replys.with(logQueryCondition.toString());
		}
		logInfos = logService.listLogStaticInfo(logQueryCondition.getAppId(),
				logQueryCondition.getJobId(), logQueryCondition.getBeginTime(),
				logQueryCondition.getEndTime());
		Element element = new Element(logQueryCondition.toString(), logInfos);
		cacheManager.getCache("logStaticCache").put(element);
		inv.getResponse().setContentType("text/html;charset=UTF-8");
		inv.getResponse().getWriter().write(logQueryCondition.toString());
		return null;
	}
	
	@Get("statisticsExportExcel/{logQueryCondition}")
	public Reply statisticsExportExcel(
			@Param("logQueryCondition") String logQueryCondition, Invocation inv) {
		if (cacheManager.getCache("logStaticCache") == null) {
			Cache cache = new Cache("logStaticCache", 100, true, false, 20, 20);
			cacheManager.addCache(cache);
		}
		String fileName = "统计列表.xls";
		if (cacheManager.getCache("logStaticCache").get(logQueryCondition.toString()) != null) {
			Collection<LogStaticInfo> logInfos = (Collection<LogStaticInfo>) cacheManager
					.getCache("logStaticCache").get(logQueryCondition.toString())
					.getValue();
			LogExcel<LogStaticInfo> logExcel = new LogExcel<LogStaticInfo>(logInfos,
					"LogStaticInfo", fileName);
			return Replys
					.with(((ByteArrayOutputStream) logExcel.getOut())
							.toByteArray()).as(Raw.class)
					.downloadFileName(fileName);
		}
		LogQueryCondition queryCondition = JSON.parseObject(logQueryCondition,
				LogQueryCondition.class);
		Collection<LogStaticInfo> logInfos = logService.listLogStaticInfo(queryCondition.getAppId(),
				queryCondition.getJobId(), queryCondition.getBeginTime(),
				queryCondition.getEndTime());
		Element element = new Element(queryCondition.toString(), logInfos);
		cacheManager.getCache("logStaticCache").put(element);
		LogExcel<LogStaticInfo> logExcel = new LogExcel<LogStaticInfo>(logInfos,
				"LogStaticInfo", fileName);
		return Replys
				.with(((ByteArrayOutputStream) logExcel.getOut()).toByteArray())
				.as(Raw.class).downloadFileName(fileName);

	}
	
	
	
	/**
	 * 加载应用的下拉选项
	 * 
	 * @param inv
	 * @return
	 */
	@Get("loadAppOption")
	public Reply loadAppOption(Invocation inv) {
		List<App> apps = appService.list();
		return Replys.with(apps).as(Json.class);
	}

	/**
	 * 加载应用的下的job下拉选项
	 * @return
	 */
	@Get("loadJobOption/{appId}")
	public Reply loadJobOption(@Param("appId") String appId) {
		List<Job> jobs = jobService.listByAppId(appId);
		return Replys.with(jobs).as(Json.class);
	}

	/**
	 * 加载应用、job的下的trigger下拉选项
	 * @return
	 */
	@Get("loadTriggerOption/{appId}/{jobId}")
	public Reply loadTriggerOption(@Param("appId") String appId,
			@Param("jobId") String jobId) {
		List<Trigger> Triggers = triggerService.listByAppidJobId(appId, jobId);
		return Replys.with(Triggers).as(Json.class);
	}

    @Get("exception/{logId}")
    public String exception(@Param("logId")String logId,Invocation inv){
        Log log = logService.findOne(logId);
        inv.addModel("exception",log.getExceptionMsg());
        return "exception";
    }
}
