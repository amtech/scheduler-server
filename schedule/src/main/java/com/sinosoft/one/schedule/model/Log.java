package com.sinosoft.one.schedule.model;
// Generated 2014-5-16 17:17:55 by One Data Tools 1.0.0


import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * Log.

 */
@Entity
@Table(name="SCHEDULE_LOG"
)
public class Log  implements java.io.Serializable {

    /**
        */
    private String id;
    /**
     */
    private String jobId;
    /**
     */
    private String appId;
    /**
     */
    private String triggerId;
    /**
     * 0代表异常1代表正常
        */
    private String status;
    /**
        */
    private Date beginTime;
    /**
        */
    private Date endTime;
    /**
        */
    private BigDecimal timeConsuming;
    /**
        */
    private String exceptionMsg;
    /**
     * 邮件发送状态:0代表未发送1代表已发送
     */
    private String sendStatus;

    @Column(name="SEND_STATUS", length=10)
    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Log() {
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="LOG_ID", length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name="JOB_ID", length=32)
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    @Column(name="APP_ID", length=32)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Column(name="TRIGGER_ID", length=32)
    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    @Column(name="STATUS")
    public String getStatus() {
    return this.status;
    }

    public void setStatus(String status) {
    this.status = status;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BEGIN_TIME", length=7)
    public Date getBeginTime() {
    return this.beginTime;
    }

    public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
    return this.endTime;
    }

    public void setEndTime(Date endTime) {
    this.endTime = endTime;
    }
    
    @Column(name="TIME_CONSUMING", precision=22, scale=0)
    public BigDecimal getTimeConsuming() {
    return this.timeConsuming;
    }

    public void setTimeConsuming(BigDecimal timeConsuming) {
    this.timeConsuming = timeConsuming;
    }
    
    @Column(name="EXCEPTION_MSG")
    public String getExceptionMsg() {
    return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
    this.exceptionMsg = exceptionMsg;
    }
/*	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
*/

}


