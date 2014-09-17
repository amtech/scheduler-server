package com.sinosoft.one.schedule.model;
// Generated 2014-5-12 15:13:56 by One Data Tools 1.0.0


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.collections.functors.FalsePredicate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Trigger.

 */
@Entity
@Table(name="SCHEDULE_TRIGGER"
)
public class Trigger  implements java.io.Serializable {

    /**
        */
    private String id;
    /**
        */
    private Job job;
    /**
        */
    private String name;
    /**
        */
    private String cronExpression;


    /**
	 * 状态码 1 执行中:表示此计划正在执行,可执行的操作有：修改，删除，停止，立即执行  
	 * 状态码 2 等待执行:表示此计划正在执行的间隔期间，可执行的操作有：修改，删除，停止，立即执行  
	 * 状态码 3 停止：表示此计划已经停止,将不会再次执行，可执行的操作有:修改，删除，开始，立即执行  
	 * 状态码 4  失效:表示此计划在agent端已经丢失，可执行的操作有：生效 V. 不可用:表示此计划所在的应用已经失去管控,不提供任何操作
	 */
    private String status;
    /**
        */
    private String recordStatus;
    /**
        */
    private String appId;
    /**
        */
    private Date createTime;
    /**
        */
    private Date modifyTime;

    public Trigger() {
    }

	
    public Trigger(String id) {
        this.id = id;
    }
   
    @Id 
    @GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="ID", unique=true, length=32)
    public String getId() {
    return this.id;
    }

    public void setId(String id) {
    this.id = id;
    }
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="JOB_ID")
    @JSONField(serialize=false)
    public Job getJob() {
    return this.job;
    }

    public void setJob(Job job) {
    this.job = job;
    }
    
    @Column(name="NAME", length=30)
    public String getName() {
    return this.name;
    }

    public void setName(String name) {
    this.name = name;
    }
    
    @Column(name="CRON_EXPRESSION", length=20)
    public String getCronExpression() {
    return this.cronExpression;
    }

    public void setCronExpression(String cronExpression) {
    this.cronExpression = cronExpression;
    }
    
    @Column(name="STATUS")
    public String getStatus() {
    return this.status;
    }

    public void setStatus(String status) {
    this.status = status;
    }
    
    @Column(name="RECORD_STATUS")
    public String getRecordStatus() {
    return this.recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
    }
    
    @Column(name="APP_ID", length=32)
    public String getAppId() {
    return this.appId;
    }

    public void setAppId(String appId) {
    this.appId = appId;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
    return this.createTime;
    }

    public void setCreateTime(Date createTime) {
    this.createTime = createTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFY_TIME", length=7)
    public Date getModifyTime() {
    return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
    this.modifyTime = modifyTime;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}


