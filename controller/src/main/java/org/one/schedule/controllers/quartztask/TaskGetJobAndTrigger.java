package org.one.schedule.controllers.quartztask;

import org.one.schedule.jmx.Communication;
import org.one.schedule.model.App;
import org.one.schedule.model.Job;
import org.one.schedule.model.Log;
import org.one.schedule.model.Trigger;
import org.one.schedule.service.AppService;
import org.one.schedule.service.JobService;
import org.one.schedule.service.LogService;
import org.one.schedule.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskGetJobAndTrigger {

	@Autowired
	private Communication communication;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private TriggerService triggerService;
	
	@Autowired
	private LogService logService;
	
	public void work() {
		List<App>apps=appService.listByIdAndFlag();
		for (App app: apps) {
			List<Job> newJobs=communication.getAllJobDetails(app);
            List<Job> oldJobs = app.getJobs();
			for (Job oldJob : oldJobs) {
                oldJob.setRecordStatus("0");
				for (Job newJob : newJobs) {
					if(newJob.getName().equals(oldJob.getName())){
                        newJob.setId(oldJob.getId());
                        newJob.setRecordStatus("1");
                        newJob.setStatus(app.getStatus());
					}
				}
				jobService.save(oldJob);
			}
			jobService.saveJobs(newJobs);
			List<Trigger> newTriggers=communication.getAllTrigger(app, newJobs);
			List<Trigger> oldTriggers=triggerService.list();
			for (Trigger oldTrigger : oldTriggers) {
                oldTrigger.setRecordStatus("0");
				for (Trigger newTrigger : newTriggers) {
					if(newTrigger.getName().equals(oldTrigger.getName())){
						newTrigger.setId(oldTrigger.getId());
                        newTrigger.setCronExpression(oldTrigger.getCronExpression());
                        newTrigger.setModifyTime(new Date());
					}
				}
				triggerService.saveTrigger(oldTrigger);
			}
			for (Trigger trigger : newTriggers) {
				Log log =logService.findLogBytriggerIdRecentTime(trigger.getName());
                if(log!=null){
                    if(log.getEndTime()==null){
                        trigger.setStatus("1");
                    }else{
                        trigger.setStatus("2");
                    }
                }else if(!app.getStatus().equals("1")){
					trigger.setStatus("4");
				}else {
                    trigger.setStatus("2");
                }
                triggerService.saveTrigger(trigger);
			}
			//triggerService.saveTriggers(triggers);
            app.setFlag("1");
            app.setModifyTime(Calendar.getInstance().getTime());
//            app.getJobs().clear();
//            app.getJobs().addAll(newJobs);
            app.setJobs(newJobs);
            appService.update(app);
		}

	}
}
