package com.sinosoft.one.schedule.controllers.job;

import java.util.List;

import com.sinosoft.one.schedule.model.App;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Param;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Get;
import com.sinosoft.one.schedule.jmx.Communication;
import com.sinosoft.one.schedule.model.Job;
import com.sinosoft.one.schedule.service.AppService;
import com.sinosoft.one.schedule.service.JobService;

@Path("")
public class JobController {
	@Autowired
	private JobService jobService;

	@Autowired
	private AppService appService;
	
	
	
	@Get("list")
	public String list(Invocation inv) {
        List<App> appList = appService.list();

//		List<Job> jobs = jobService.list();
//		for (Job job : jobs) {
//			job.setApp(appService.findByJobId(job.getId()));
//		}
		inv.addModel("appList", appList);
		return "joblist";
	}
	
	@Get("listByAppId/{appid}/{appname}")
	public String listByAppId(@Param("appid")String appid,@Param("appname")String appname,Invocation inv) {
		List<Job> jobs = jobService.listByAppId(appid);
		System.out.println(jobs.size());
		inv.addModel("jobs", jobs);
		inv.addModel("appname", appname);
		inv.addModel("appid", appid);
		return "applist_joblist";
	}
	
}
