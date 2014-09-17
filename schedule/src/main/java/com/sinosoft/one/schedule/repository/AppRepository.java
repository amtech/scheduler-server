package com.sinosoft.one.schedule.repository;
// Generated 2014-5-12 15:13:57 by One Data Tools 1.0.0

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sinosoft.one.data.jade.annotation.SQL;
import com.sinosoft.one.schedule.model.App;

public interface AppRepository extends PagingAndSortingRepository<App, String> {
	
	//@SQL("select * from SCHEDULE_APP where RECORD_STATUS='1' ")
    @Query("select a from App a where a.recordStatus='1'")
	public List<App> list();
    //@SQL("select * from SCHEDULE_APP where RECORD_STATUS='1' and flag='0' ")
    @Query("select a from App a where a.recordStatus='1' and a.flag='0'")
    public List<App> listByIdAndFlag();
	
	@SQL("select STATUS from SCHEDULE_APP where RECORD_STATUS='1' and ID=?1 ")
	public String checkStatus(String id);
	
	
	@SQL("select * from SCHEDULE_APP where id=(select APP_ID from SCHEDULE_JOB where ID =?1)")
	public App findAppByJobid(String jobid);
	
	//@SQL("select * from SCHEDULE_APP where MATCHKEY=?1")
    @Query("select  a from App a where a.matchkey = ?1")
	public App findAppByMatchkey(String matchkey);
	
	//@SQL("select * from SCHEDULE_APP where name=?1")
    @Query("select a from App a where  a.name=?1")
	public App findAppByName(String name);
}