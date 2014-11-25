package org.one.schedule.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.one.schedule.model.App;
import org.one.schedule.repository.AppRepository;

@Service
public class AppService {

	@Autowired
	private AppRepository appRepository;

	public Map<String, String> save(App app) {
		App existApp;
		Map<String, String> message = new HashMap<String, String>();
		existApp = appRepository.findAppByName(app.getName());
        if (existApp == null) {
            app.setCreateTime(Calendar.getInstance().getTime());
            app.setRecordStatus("1");
            app.setStatus("1");
            app.setFlag("0");
            app.setCreateTime(Calendar.getInstance().getTime());
            appRepository.save(app);
            message.put("appName",app.getName());
            message.put("statusCode", "200");
            return message;
        }else{
            if(!existApp.getIp().equals(app.getIp())||!existApp.getPort().equals(app.getPort())){
                message.put("statusCode", "501");
                message.put("errorMsg", "应用名重复");
                return message;
            }else{
                existApp.setFlag("0");
                existApp.setStatus("1");
                existApp.setModifyTime(Calendar.getInstance().getTime());
                appRepository.save(existApp);
                message.put("appName",app.getName());
                message.put("statusCode", "200");
                return message;
            }
        }
	}

	public List<App> list() {
		return appRepository.list();
	}

    /**
     * 获取未获取任务的应用
     * @return
     */
    public List<App> listByIdAndFlag(){
        return appRepository.listByIdAndFlag();
    }

	public App findByJobId(String jobid) {
		return appRepository.findAppByJobid(jobid);
	}

	/**
	 * 根据id获取应用状态
	 * 
	 * @param id
	 * @return
	 */
	public String checkStatus(String id) {
		return appRepository.checkStatus(id);
	}

    /**
     * 更新app信息
     * @param app
     */
    public void update(App app){
        appRepository.save(app);
    }

    public App findById(String id){

        return appRepository.findOne(id);
    }
}
