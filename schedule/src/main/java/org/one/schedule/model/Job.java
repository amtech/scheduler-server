package org.one.schedule.model;
// Generated 2014-5-12 15:13:56 by One Data Tools 1.0.0


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Job.

 */
@Entity
@Table(name="SCHEDULE_JOB"
)
public class Job  implements java.io.Serializable {

    /**
        */
    private String id;

    /**
        */
    private String name;
    /**
        */
    private String status;
    /**
        */
    private String recordStatus;
    /**
        */
    private String class_;
    /**
        */
    private Date createTime;
    /**
        */
    private Date modifyTime;
    /**
        */
    private List<Trigger> triggers = new ArrayList<Trigger>(0) ;

    public Job() {
    }

	
    public Job(String id) {
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
    
    @Column(name="NAME", length=30)
    public String getName() {
    return this.name;
    }

    public void setName(String name) {
    this.name = name;
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
    
    @Column(name="CLASS")
    public String getClass_() {
    return this.class_;
    }

    public void setClass_(String class_) {
    this.class_ = class_;
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
    @OneToMany(fetch=FetchType.EAGER, mappedBy="job")
    @Fetch(FetchMode.SUBSELECT)
    @JSONField(serialize=false)
    public List<Trigger> getTriggers() {
    return this.triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
    this.triggers = triggers;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}


