package org.one.schedule.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;

public class LogInfo implements Serializable {
	/**
	 * 
	 */

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String appName;

	private String jobName;

	private String triggerName;

	private Date beginTime;

	private Date endTime;

	private String formetTime;
	
	private String status;
	
	private Clob exceptionMsg;
	
	private BigDecimal timeConsuming;

	

	

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getTimeConsuming() {
		formatTime();
		return timeConsuming;
	}

	public void setTimeConsuming(BigDecimal timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public Clob getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(Clob exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getFormetTime() {
		formatTime();
		return formetTime;
	}

	public void setFormetTime(String formetTime) {
		this.formetTime = formetTime;
	}

	void formatTime() {
		if (timeConsuming == null) {
			timeConsuming = new BigDecimal(new Date().getTime()
					- beginTime.getTime());
			
		} 
		long time = timeConsuming.longValue() / 1000;
		int s = (int) (time % 60);
		int m = (int) (time / 60 % 60);
		int h = (int) (time / 3600);
		formetTime = h + "小时" + m + "分" + s + "秒";
	}
}
