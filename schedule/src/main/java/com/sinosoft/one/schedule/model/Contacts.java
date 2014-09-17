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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * Contacts.

 */
@Entity
@Table(name="SCHEDULE_CONTACTS"
)
public class Contacts  implements java.io.Serializable {

    /**
        */
    private String id;

    /**
        */
    private String name;
    /**
        */
    private String mail;
    /**
        */
    private Long phoneNo;
    /**
        */
    private String recordStatus;
    /**
        */
    private Date recordTime;
    /**
        */
    private Date modifyTime;

    public Contacts() {
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
    
    @Column(name="MAIL", length=50)
    public String getMail() {
    return this.mail;
    }

    public void setMail(String mail) {
    this.mail = mail;
    }
    
    @Column(name="PHONE_NO", precision=12, scale=0)
    public Long getPhoneNo() {
    return this.phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
    this.phoneNo = phoneNo;
    }
    
    @Column(name="RECORD_STATUS")
    public String getRecordStatus() {
    return this.recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RECORD_TIME", length=7)
    public Date getRecordTime() {
    return this.recordTime;
    }

    public void setRecordTime(Date recordTime) {
    this.recordTime = recordTime;
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


