package org.one.schedule.service;

import org.one.schedule.jmx.Communication;
import org.one.schedule.model.App;
import org.one.schedule.model.Job;
import org.one.schedule.model.Trigger;
import org.one.schedule.repository.AppRepository;
import org.one.schedule.repository.JobRepository;
import org.one.schedule.repository.TriggerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TriggerService {

	@Autowired
	private TriggerRepository triggerRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private AppRepository appRepository;
	@Autowired
	private Communication communication;

	public List<Trigger> listByAppidJobId(String appid, String jobid) {
		return triggerRepository.listByAppidJobId(appid, jobid);
	}

	public List<Trigger> list() {
		return triggerRepository.list();
	}

	public void saveTriggers(List<Trigger> triggers) {
		triggerRepository.save(triggers);
	}

	public void saveTrigger(Trigger trigger) {
		triggerRepository.save(trigger);
	}

	public Trigger getOne(String triggerid) {
		return triggerRepository.getOne(triggerid);
	}

	/**
	 * 增加计划和修改计划
	 * 
	 * @param
	 * @return
	 */
	public Map<String, String> save(String appId, String jobid,
			String triggerid, String triggerName, String cornValue) {
		Map<String, String> map = new HashMap<String, String>();
		// 1.检测应用现在是否可用
		App app = appRepository.findOne(appId);
		if (app == null) {
			map.put("status", "0");
			map.put("msg", "app不存在");
			return map;
		}
		if (!app.getStatus().equals("1")) {
			map.put("status", "0");
			map.put("msg", "app不可用");
			return map;
		}

		Job job = jobRepository.findOne(jobid);
		Trigger trigger = new Trigger();
		if (triggerid != null) {
			trigger.setId(triggerid);
		}else {
            Trigger t = triggerRepository.findByName(triggerName);
            if(t!=null){
                map.put("status", "0");
                map.put("msg", triggerName+"已存在!");
                return map;
            }
        }
		trigger.setAppId(appId);
		trigger.setJob(job);
		trigger.setCronExpression(cornValue);
		trigger.setName(triggerName);
		trigger.setRecordStatus("1");
		trigger.setStatus("2");
		trigger.setCreateTime(new Date());
		// 2.agent端增加计划
		try {
			communication.save(app, trigger);
			map.put("status", "1");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "-1");
			map.put("msg", "客户端操作失败");
		}
		if (map.get("status") == "1") {
			triggerRepository.save(trigger);
			map.put("msg", "添加成功");
		}
		return map;
	}

	/**
	 * 删除计划
	 * 
	 * @param
	 * @return
	 */
	public Map<String, String> delete(String appId, String triggerId) {
		App app = appRepository.findOne(appId);
		Trigger trigger = triggerRepository.getOne(triggerId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			communication.delete(app, trigger);
			map.put("status", "1");

		} catch (Exception e) {
			map.put("status", "-1");
			map.put("msg", "客户端操作失败");
		}
		if (map.get("status").equals("1")) {
			triggerRepository.delete(trigger);
			map.put("msg", "删除成功");
		}
		return map;
	}

	/**
	 * 计划停止
	 * 
	 * @param
	 * @return
	 */
	public Map<String, String> pause(String appId, String triggerId) {
		App app = appRepository.findOne(appId);
		Trigger trigger = triggerRepository.getOne(triggerId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			communication.pauseTrigger(app, trigger);
			map.put("status", "1");
		} catch (Exception e) {
			map.put("status", "-1");
			map.put("msg", "客户端操作失败");
			return map;
		}
		if (map.get("status").equals("1")) {
			triggerRepository.update(triggerId,"3");
			map.put("msg", "操作成功");
		}
		return map;
	}

	/**
	 * 计划开始
	 * 
	 * @param
	 * @return
	 */
	public Map<String, String> resume(String appId, String triggerId) {
		App app = appRepository.findOne(appId);
		Trigger trigger = triggerRepository.getOne(triggerId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			communication.resumeTrigger(app, trigger);
			map.put("status", "1");
		} catch (Exception e) {
			map.put("status", "-1");
			map.put("msg", "客户端操作失败");
			return map;
		}
		if (map.get("status").equals("1")) {
			triggerRepository.update(triggerId,"2");
			map.put("msg", "操作成功");
		}
		return map;
	}

	/**
	 * 立即执行
	 * 
	 * @param
	 * @return
	 */
	public Map<String, String> triggerJob(String appId, String jobId) {
		App app = appRepository.findOne(appId);
		Job job = jobRepository.getOne(jobId);
		Map<String, String> map = new HashMap<String, String>();
		try {
			communication.triggerJob(app, job);
		} catch (Exception e) {
			map.put("status", "-1");
			map.put("msg", "客户端操作失败");
			return map;
		}
        map.put("status", "-1");
        map.put("msg", "执行成功");
		return map;
	}

}
