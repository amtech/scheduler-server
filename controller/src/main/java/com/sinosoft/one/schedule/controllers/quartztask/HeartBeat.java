package com.sinosoft.one.schedule.controllers.quartztask;

import com.sinosoft.one.schedule.model.App;
import com.sinosoft.one.schedule.model.Contacts;
import com.sinosoft.one.schedule.model.Job;
import com.sinosoft.one.schedule.service.AppService;
import com.sinosoft.one.schedule.service.ContactsService;
import com.sinosoft.one.schedule.service.JobService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HeartBeat {

	/**
	 * 心跳缓存 每个应用使用 Matchkey为Key， 最后一次时间与name为值
	 */
    @Autowired
	public CacheManager cacheManager;

    @Autowired
	private AppService appService;
    @Autowired
    private JobService jobService;
    @Autowired
    private ContactsService contactsService;

	public void work() {
		List<App> apps = appService.list();
		for (App app : apps) {
			app.setStatus("0");
            List<Job> jobs = app.getJobs();
            for(Job job:jobs){
                job.setStatus("0");
                job.setModifyTime(Calendar.getInstance().getTime());
            }
            if(cacheManager.getCache("appCache")!=null){
                if(cacheManager.getCache("appCache").getQuiet(app.getName())!=null){
                    Map<String, Object> map=(Map<String, Object>)cacheManager.getCache("appCache").get(app.getName()).getValue();
                    if(map.get("name").equals(app.getName())){
                        Date d = (Date) map.get("date");
                        if ((Calendar.getInstance().getTime().getTime() - d.getTime()) < 1000 * 60) {
                            app.setStatus("1");
                            for(Job job:jobs){
                                job.setStatus("1");
                                job.setModifyTime(Calendar.getInstance().getTime());
                            }
                        }
                    }
                }
            }
            jobService.saveJobs(jobs);
            List<Contacts> contactses = contactsService.listByAppId(app.getId());
            app.setContactses(contactses);
            app.setJobs(jobs);
            appService.update(app);
		}
	}
}
