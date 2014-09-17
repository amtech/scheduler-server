package com.sinosoft.one.schedule.vo;

import com.alibaba.fastjson.JSON;

public class LogQueryCondition {

	private String appId;
	
	private String jobId;
	
	private String triggerId;
	
	private String status;
	
	private String beginTime;
	
	private String endTime;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		
//		return "LogQueryCondition [appId=" + appId + ", jobId=" + jobId
//				+ ", triggerId=" + triggerId + ", status=" + status
//				+ ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
		return JSON.toJSONString(this);
	}

 
	
	
}
