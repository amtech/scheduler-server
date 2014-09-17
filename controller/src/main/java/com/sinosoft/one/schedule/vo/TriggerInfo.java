package com.sinosoft.one.schedule.vo;

public class TriggerInfo {
	
	private String triggerid;
	
	private String name;
	
	private String cronExpression;

	private String status;
	
	private String appId;

	private String recordStatus;

	private String lastStartTime;
	
	private String nextStartTime;
	
	public String getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(String lastStartTime) {
		this.lastStartTime = lastStartTime;
	}
 
	public String getNextStartTime() {
		return nextStartTime;
	}

	public void setNextStartTime(String nextStartTime) {
		this.nextStartTime = nextStartTime;
	}

	public String getTriggerid() {
		return triggerid;
	}

	public void setTriggerid(String triggerid) {
		this.triggerid = triggerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	
}
