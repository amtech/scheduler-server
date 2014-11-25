package org.one.schedule.controllers.trigger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Param;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Get;
import com.sinosoft.one.mvc.web.annotation.rest.Post;
import com.sinosoft.one.mvc.web.instruction.reply.Reply;
import com.sinosoft.one.mvc.web.instruction.reply.Replys;
import com.sinosoft.one.mvc.web.instruction.reply.transport.Json;
import org.one.schedule.model.Job;
import org.one.schedule.model.Log;
import org.one.schedule.model.Trigger;
import org.one.schedule.service.JobService;
import org.one.schedule.service.LogService;
import org.one.schedule.service.TriggerService;
import org.one.schedule.vo.TriggerInfo;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Path("")
public class TriggerController {

	@Autowired
	private TriggerService triggerService;

	@Autowired
	private JobService jobService;

	@Autowired
	private LogService logService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Get("listByAppidJobId/{jobid}/{appid}")
	public String listByAppidJobId(@Param("jobid") String jobid,
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
		return "triggerinfo";
	}

	@Post("addTrigger")
	public Reply addTrigger(@Param("appId") String appId,
			@Param("jobId") String jobId,
			@Param("triggerName") String triggerName,
			@Param("cronValue") String cronValue) {
		Map map = triggerService.save(appId, jobId,null, triggerName, cronValue);
		return Replys.with(map).as(Json.class);
	}

	@Post("preparedModify/{triggerId}/{jobId}")
	public String preparedModify(@Param("triggerId")String triggerId,@Param("jobId")String jobId,Invocation inv) {
		Trigger trigger=triggerService.getOne(triggerId);
		inv.addModel("trigger", trigger);
		inv.addModel("jobId", jobId);
		return "updataTrigger";
	}
	
	@Post("modify")
	public Reply modify(@Param("appId")String appId,@Param("jobId")String jobId,@Param("triggerId")String triggerId,@Param("triggerName")String triggerName,@Param("cronValue")String cronValue,Invocation inv){
		Map<String, String>map=triggerService.save(appId, jobId,triggerId, triggerName, cronValue);
		return Replys.with(map).as(Json.class);
	}
	
	@Post("delete")
	public Reply delete(@Param("appId")String appId,@Param("triggerId")String triggerId){
		Map<String, String>map=triggerService.delete(appId ,triggerId);
		return Replys.with(map).as(Json.class);
	}
	
	@Post("pause/{appId}/{triggerId}")
	public Reply pause(@Param("appId")String appId,@Param("triggerId")String triggerId){
		Map<String, String>map=triggerService.pause(appId ,triggerId);
		return Replys.with(map).as(Json.class);
	}
	
	@Post("resume/{appId}/{triggerId}")
	public Reply resume(@Param("appId")String appId,@Param("triggerId")String triggerId){
		Map<String, String>map=triggerService.resume(appId ,triggerId);
		return Replys.with(map).as(Json.class);
	}
	
	@Post("triggerJob/{appId}/{jobId}")
	public Reply triggerJob(@Param("appId")String appId,@Param("jobId")String jobId){
		Map<String, String>map=triggerService.triggerJob(appId ,jobId);
		return Replys.with(map).as(Json.class);
	}
	
	@Post("log/startRecord")
	public Reply startRecord(@Param("appName") String appName,
			@Param("jobName") String jobName,
			@Param("beginTime") String beginTime,
			@Param("triggerName") String triggerName
			) {
		Map<String, String> message = new HashMap<String, String>();
		try {
			message = logService.save(appName, null, jobName, beginTime, null,
					triggerName, null);
		} catch (Exception e) {
			message.put("500", e.getMessage());
		}
		return Replys.with(message).as(Json.class);
	}

	@Post({ "log/endRecord" })
	public Reply endRecord(@Param("id") String id,
                           @Param("appName") String appName,
            @Param("jobName") String jobName,
			@Param("triggerName") String triggerName,
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("exceptionMsg") String exceptionMsg
			) {
		Map<String, String> message = new HashMap<String, String>();
		try {
			message = logService.save(appName, id, jobName, beginTime, endTime,
					triggerName, exceptionMsg);
		} catch (Exception e) {
			message.put("500", e.getMessage());
		}
		return Replys.with(message).as(Json.class);
	}

	/**
	 * 接收日志缓存文件
	 * 
	 * @param invocation
	 * @return
	 */
	@Post("log/saveRecord")
	public Reply saveRecord(Invocation invocation) throws IOException {
		Map<String, String> message = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) invocation
				.getRequest();
		MultipartFile multipartFile = multipartRequest.getFile("logFile");
		if (!multipartFile.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = invocation.getRequest().getSession()
						.getServletContext().getRealPath("/")
						+ "upload/" + multipartFile.getOriginalFilename();
				File file = new File(filePath);
				file.mkdirs();
				// 转存文件
				multipartFile.transferTo(file);
				ZipFile zipFile = new ZipFile(file);
				Enumeration e = zipFile.entries();
				while (e.hasMoreElements()) {
					ZipEntry zipEntry = (ZipEntry) e.nextElement();
					InputStream is = zipFile.getInputStream(zipEntry);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					int count = 0;
					char[] b = new char[1024 * 1024];
					String resolve = null;
					while ((count = br.read(b)) != -1) {
						String data = new String(b, 0, count);
						resolve = "[" + data + "]";
					}
					JSONArray jsonArray = JSONArray.parseArray(resolve);
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						String appName = jsonObject.getString("appName");
						String jobName = jsonObject.getString("jobName");
						String triggerName = jsonObject
								.getString("triggerName");
						String beginTime = jsonObject.getString("beginTime");
						String endTime = jsonObject.getString("endTime");
						String id = jsonObject.getString("id");
						String exceptionMsg = jsonObject
								.getString("exceptionMsg");
						message = logService.save(appName, id, jobName,
								beginTime, endTime, triggerName, exceptionMsg);
					}
					is.close();
					br.close();
				}
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Replys.with(message).as(Json.class);
	}

}
