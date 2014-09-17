package com.sinosoft.one.schedule.repository;
// Generated 2014-5-12 15:13:57 by One Data Tools 1.0.0

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sinosoft.one.data.jade.annotation.SQL;
import com.sinosoft.one.schedule.model.Job;
import com.sinosoft.one.schedule.model.Trigger;

public interface JobRepository extends PagingAndSortingRepository<Job, String> {
	
	
	@SQL("select * from SCHEDULE_JOB where RECORD_STATUS='1' ")
	public List<Job> list();
	
	@SQL("select *  from SCHEDULE_JOB where RECORD_STATUS='1' and APP_ID= ?1 " )
    //@Query("select j from Job j where j.recordStatus='1' and j.app.id=?1")
	public List<Job> listByAppId(String appid);
	
	@SQL("select * FROM SCHEDULE_JOB where id=?1")
	public Job getOne(String jobId);
	
	@SQL("select *  from SCHEDULE_JOB where RECORD_STATUS='1' and NAME= ?1 " )
	public Job findByName(String jobName);
}

