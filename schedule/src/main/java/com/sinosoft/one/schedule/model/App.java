package com.sinosoft.one.schedule.model;
// Generated 2014-5-12 15:13:56 by One Data Tools 1.0.0


import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * App.

 */
@Entity
@Table(name="SCHEDULE_APP"
)
public class App  implements java.io.Serializable {

    /**
        */
    private String id;
    /**
        */
    private String name;
    /**
        */
    private String ip;
    /**
        */
    private BigDecimal port;
    /**
        */
    private String status;
    /**
        */
    private String recordStatus;
    /**
        */
    private String matchkey;
    /**
        */
    private Date createTime;
    /**
        */
    private Date modifyTime;
    /**
        */
    private String flag;
    /**
        */
    private List<Contacts> contactses = new ArrayList<Contacts>(0) ;
    /**
        */
    private List<Job> jobs = new ArrayList<Job>(0) ;

    public App() {
    }

	
    public App(String id, String name, String matchkey) {
        this.id = id;
        this.name = name;
        this.matchkey = matchkey;
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
    
    @Column(name="IP", length=15)
    public String getIp() {
    return this.ip;
    }

    public void setIp(String ip) {
    this.ip = ip;
    }
    
    @Column(name="PORT", precision=22, scale=0)
    public BigDecimal getPort() {
    return this.port;
    }

    public void setPort(BigDecimal port) {
    this.port = port;
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
    @GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="MATCHKEY", length=32)
    public String getMatchkey() {
    return this.matchkey;
    }

    public void setMatchkey(String matchkey) {
    this.matchkey = matchkey;
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
    
    @Column(name="FLAG")
    public String getFlag() {
    return this.flag;
    }

    public void setFlag(String flag) {
    this.flag = flag;
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="APP_ID")
   /* @Fetch(FetchMode.SUBSELECT)*/
    public List<Contacts> getContactses() {
    return this.contactses;
    }

    public void setContactses(List<Contacts> contactses) {
    this.contactses = contactses;
    }
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    /*@Fetch(FetchMode.SUBSELECT)*/
    @JSONField(serialize=false)
    @JoinColumn(name="APP_ID")
    public List<Job> getJobs() {
    return this.jobs;
    }

    public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}


