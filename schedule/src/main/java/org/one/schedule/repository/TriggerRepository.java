package org.one.schedule.repository;
// Generated 2014-5-12 15:13:57 by One Data Tools 1.0.0

import java.util.List;

import org.one.schedule.model.Trigger;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sinosoft.one.data.jade.annotation.SQL;

public interface TriggerRepository extends PagingAndSortingRepository<Trigger, String> {
	
	@SQL("select * FROM SCHEDULE_TRIGGER where APP_ID=?1 and JOB_ID=?2")
	public List<Trigger> listByAppidJobId(String appid ,String jobid);
	
	@SQL("select * FROM SCHEDULE_TRIGGER where NAME=?1")
	public Trigger findByName(String tiggerName);
	
	@SQL("select * FROM SCHEDULE_TRIGGER where id=?1")
	public Trigger getOne(String triggerID);
	
	@SQL("select * FROM SCHEDULE_TRIGGER where RECORD_STATUS='1' ")
    //@Query("select t from Trigger t where t.recordStatus='1' ")
	public List<Trigger> list();

    @SQL("UPDATE SCHEDULE_TRIGGER SET STATUS = ?2 WHERE ID = ?1")
    public void update(String id,String stauts);
	
}

