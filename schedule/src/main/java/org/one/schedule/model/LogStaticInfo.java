package org.one.schedule.model;

import java.io.Serializable;
import java.util.Date;

public class LogStaticInfo  implements Serializable{
	
	private String jobName;
	
	private Date beginTime;

	private Date endTime;
	
//	private String formetTime;
	
	private int executionTimes;
	
	private int failuresTimes;
	
	private int successTimes;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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

//	public String getFormetTime() {
//		formatTime();
//		return formetTime;
//	}
//
//	public void setFormetTime(String formetTime) {
//		this.formetTime = formetTime;
//	}


	public int getExecutionTimes() {
		return executionTimes;
	}

	public void setExecutionTimes(int executionTimes) {
		this.executionTimes = executionTimes;
	}

	public int getFailuresTimes() {
		return failuresTimes;
	}

	public void setFailuresTimes(int failuresTimes) {
		this.failuresTimes = failuresTimes;
	}

	public int getSuccessTimes() {
		return successTimes;
	}

	public void setSuccessTimes(int successTimes) {
		this.successTimes = successTimes;
	}
	
//	void formatTime() {
//		BigDecimal t;
//		if (endTime == null) {
//			t = new BigDecimal(new Date().getTime()- beginTime.getTime());
//
//		}else{
//			t=new BigDecimal(endTime.getTime()- beginTime.getTime());
//		}
//		long time = t.longValue() / 1000;
//		int s = (int) (time % 60);
//		int m = (int) (time / 60 % 60);
//		int h = (int) (time / 3600);
//		formetTime = h + "小时" + m + "分" + s + "秒";
//	}
	
}
