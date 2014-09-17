package com.sinosoft.one.schedule.repository;
// Generated 2014-5-12 15:13:57 by One Data Tools 1.0.0

import com.sinosoft.one.data.jade.annotation.SQL;
import com.sinosoft.one.schedule.model.Log;
import com.sinosoft.one.schedule.model.LogInfo;
import com.sinosoft.one.schedule.model.LogStaticInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LogRepository extends PagingAndSortingRepository<Log,String> {
	
	//@SQL("select * FROM SCHEDULE_LOG where APP_ID=?1 and JOB_ID=?2 and trigger_id in(?3) and end_time is null")
	@Query("FROM Log a where a.appId=?1 and a.jobId=?2 and a.triggerId in(?3) order by a.beginTime desc")
	public List<Log> findLogsByAppidJobID(String appid,String jobid ,List<String> tiggerids);
	
	//@Query(" FROM Log where id.appId=?1 and id.jobId=?2 and id.triggerId=?3 and status=?4 and beginTime= to_date(?5,'yyyy-MM-dd 24hh:mi:ss') and endTime=to_date(?6,'yyyy-MM-dd 24hh:mi:ss')")
	
	/*@SQL("select (select name from SCHEDULE_App app where app.id=l.APP_ID ) \"appName\" , "
			+ " (select name from SCHEDULE_JOB job where job.id=l.JOB_ID ) \"jobName\" ,"
			+ " (select name from SCHEDULE_TRIGGER trigger where trigger.id=l.TRIGGER_ID ) \"triggerName\","
			+ " STATUS \"status\" ,"
			+ " BEGIN_TIME \"beginTime\" ,"
			+ " END_TIME \"endTime\" ,"
			+ " TIME_CONSUMINGFROM \"timeConsuming\" ,"
			+ " EXCEPTION_MSG \"exceptionMsg\" "
			+ " from SCHEDULE_LOG l where "
			+ "( ?1 is null or l.APP_ID in (?1)) and "
			+ "( ?2 is null or l.JOB_ID in (?2)) and "
			+ "( ?3 is null or l.TRIGGER_ID in (?3)) and "
			+ "( ?4 is null or l.STATUS in (?4)) and "
			+ "( ?5 is null or l.BEGIN_TIME in (to_date(?5,?7))) and "
			+ "( ?6 is null or l.END_TIME in (to_date(?6,?7))) ")
			
			*
			*sql bug ����ֵ����ָ��
			* ��ݿ����֧�ֽ��п��ж�
			*/
/*	@SQL("select (select name from SCHEDULE_App app where app.id=l.APP_ID ) \"appName\" , "
			+ " (select name from SCHEDULE_JOB job where job.id=l.JOB_ID ) \"jobName\" ,"
			+ " (select name from SCHEDULE_TRIGGER trigger where trigger.id=l.TRIGGER_ID ) \"triggerName\","
			+ " STATUS \"status\" ,"
			+ " BEGIN_TIME \"beginTime\" ,"
			+ " END_TIME \"endTime\" ,"
			+ " TIME_CONSUMINGFROM \"timeConsuming\" ,"
			+ " EXCEPTION_MSG \"exceptionMsg\" "
			+ " from SCHEDULE_LOG l where "
			+ "( ?1 is null or l.APP_ID in (?1)) and "
			+ "( ?2 is null or l.JOB_ID in (?2)) and "
			+ "( ?3 is null or l.TRIGGER_ID in (?3)) and "
			+ "( ?4 is null or l.STATUS in (?4)) and "
			+ "( ?5 is null or l.BEGIN_TIME in (to_date(?5,?7))) and "
			+ "( ?6 is null or l.END_TIME in (to_date(?6,?7))) ")*/
	//@Query("select (select name from App app where app.id=l.id.appId )  \"appName\"   FROM Log l where (?1 is null or  l.id.appId in (?1)) and (?2 is null or l.id.jobId in (?2)) and (?3 is null or l.id.triggerId in (?3)) and (?4 is null or status in (?4)) and ( ?5 is null or beginTime in (to_date(?5,?7))) and ( ?6 is null or endTime in (to_date(?6,?7)))")
	 
	/**
	 * 选择开始时间的 开始与结束 两个条件
	 * @param appid
	 * @param jobid
	 * @param triggerid
	 * @param status
	 * @return
	 */
	@SQL("select (select name from SCHEDULE_App a where a.id=APP_ID )  \"appName\" ,"
			+ "(select name from SCHEDULE_JOB j where j.id=JOB_ID ) \"jobName\" ,"
			+ "(select name from SCHEDULE_TRIGGER t where t.id=TRIGGER_ID ) \"triggerName\" ,"
			+ " STATUS \"status\" ,"
			+ " BEGIN_TIME \"beginTime\" ,"
			+ " END_TIME \"endTime\" ,"
			+ " TIME_CONSUMING \"timeConsuming\" ,"
			+ " EXCEPTION_MSG \"exceptionMsg\", "
            + " LOG_ID \"id\" "
			+ "from SCHEDULE_LOG where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( ?3 is null or TRIGGER_ID in (?3)) and "
			+ "( ?4 is null or STATUS in (?4)) and "
			+ "( BEGIN_TIME between to_date(?5,?7) and to_date(?6,?7))" )
	public List<LogInfo> listLogInfo(String appid,String jobid,String triggerid,String status,String begintime,String begintime_end,String dateformate);
	
	/**
	 * 选择开始时间的 开始 条件
	 * @param appid
	 * @param jobid
	 * @param triggerid
	 * @param status
	 * @return
	 */
	@SQL("select (select name from SCHEDULE_App a where a.id=APP_ID )  \"appName\" ,"
			+ "(select name from SCHEDULE_JOB j where j.id=JOB_ID ) \"jobName\" ,"
			+ "(select name from SCHEDULE_TRIGGER t where t.id=TRIGGER_ID ) \"triggerName\" ,"
			+ " STATUS \"status\" ,"
			+ " BEGIN_TIME \"beginTime\" ,"
			+ " END_TIME \"endTime\" ,"
			+ " TIME_CONSUMING \"timeConsuming\" ,"
			+ " EXCEPTION_MSG \"exceptionMsg\" ,"
            + " LOG_ID \"id\" "
			+ "from SCHEDULE_LOG where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( ?3 is null or TRIGGER_ID in (?3)) and "
			+ "( ?4 is null or STATUS in (?4)) and "
			+ "(BEGIN_TIME>=to_date(?5,?6))")
	public List<LogInfo> listLogInfo_begintime(String appid,String jobid,String triggerid,String status,String begintime,String dateformate);
	
	
	/**
	 * 选择开始时间的 结束 条件
	 * @param appid
	 * @param jobid
	 * @param triggerid
	 * @param status
	 * @return
	 */
	@SQL("select (select name from SCHEDULE_App a where a.id=APP_ID )  \"appName\" ,"
			+ "(select name from SCHEDULE_JOB j where j.id=JOB_ID ) \"jobName\" ,"
			+ "(select name from SCHEDULE_TRIGGER t where t.id=TRIGGER_ID ) \"triggerName\" ,"
			+ " STATUS \"status\" ,"
			+ " BEGIN_TIME \"beginTime\" ,"
			+ " END_TIME \"endTime\" ,"
			+ " TIME_CONSUMING \"timeConsuming\" ,"
			+ " EXCEPTION_MSG \"exceptionMsg\" ,"
            + " LOG_ID \"id\" "
			+ "from SCHEDULE_LOG where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( ?3 is null or TRIGGER_ID in (?3)) and "
			+ "( ?4 is null or STATUS in (?4)) and "
			+ "( BEGIN_TIME<=to_date(?5,?6))")
	public List<LogInfo> listLogInfo_begintime_end(String appid,String jobid,String triggerid,String status,String begintime_end,String dateformate);
	
	/**
	 * 不选择开始时间条件
	 * @param appid
	 * @param jobid
	 * @param triggerid
	 * @param status
	 * @return
	 */
	@SQL("select (select name from SCHEDULE_App a where a.id=APP_ID )  \"appName\" ,"
			+ "(select name from SCHEDULE_JOB j where j.id=JOB_ID ) \"jobName\" ,"
			+ "(select name from SCHEDULE_TRIGGER t where t.id=TRIGGER_ID ) \"triggerName\" ,"
			+ " STATUS \"status\" ,"
			+ " BEGIN_TIME \"beginTime\" ,"
			+ " END_TIME \"endTime\" ,"
			+ " TIME_CONSUMING \"timeConsuming\" ,"
			+ " EXCEPTION_MSG \"exceptionMsg\" ,"
            + " LOG_ID \"id\" "
			+ "from SCHEDULE_LOG where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( ?3 is null or TRIGGER_ID in (?3)) and "
			+ "( ?4 is null or STATUS in (?4)) ")
	public List<LogInfo> listLogInfo(String appid,String jobid,String triggerid,String status);

	/** 日志统计---------------------------------------------**/

	@SQL("select (select name from schedule_job j where j.id=l.job_id ) \"jobName\", "
			+ "min(BEGIN_TIME) \"beginTime\", "
			+ "case when (select STATUS from schedule_job j where j.id=l.job_id ) ='1' then null else max(END_TIME) end \"endTime\", "
			+ "count(l.job_id) \"executionTimes\", "
			+ "sum(case  when l.STATUS ='1' then 1 else 0 end) \"successTimes\", "
			+ "sum(case  when l.STATUS ='0' then 1 else 0 end) \"failuresTimes\" "
			+ "from  SCHEDULE_LOG l where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) group by l.job_id")
	public List<LogStaticInfo> listStaticInfo(String appid,String jobid);
	
	
	@SQL("select (select name from schedule_job j where j.id=l.job_id ) \"jobName\", "
			+ "min(BEGIN_TIME) \"beginTime\", "
			+ "case when (select STATUS from schedule_job j where j.id=l.job_id ) ='1' then null else max(END_TIME) end \"endTime\", "
			+ "count(l.job_id) \"executionTimes\", "
			+ "sum(case  when l.STATUS ='1' then 1 else 0 end) \"successTimes\" ,"
			+ "sum(case  when l.STATUS ='0' then 1 else 0 end) \"failuresTimes\" "
			+ "from  SCHEDULE_LOG l where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( BEGIN_TIME>=to_date(?3,?4)) group by l.job_id")
	public List<LogStaticInfo> listStaticInfo_begintime(String appid,String jobid,String begintime,String dateformate);
	
	
	@SQL("select (select name from schedule_job j where j.id=l.job_id ) \"jobName\", "
			+ "min(BEGIN_TIME) \"beginTime\", "
			+ "case when (select STATUS from schedule_job j where j.id=l.job_id ) ='1' then null else max(END_TIME) end \"endTime\", "
			+ "count(l.job_id) \"executionTimes\", "
			+ "sum(case  when l.STATUS ='1' then 1 else 0 end) \"successTimes\", "
			+ "sum(case  when l.STATUS ='0' then 1 else 0 end) \"failuresTimes\" "
			+ "from  SCHEDULE_LOG l where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( BEGIN_TIME<=to_date(?3,?4)) group by l.job_id")
	public List<LogStaticInfo> listStaticInfo_begintime_end(String appid,String jobid,String begintime_end,String dateformate);
	
	@SQL("select (select name from schedule_job j where j.id=l.job_id ) \"jobName\", "
			+ "min(BEGIN_TIME) \"beginTime\", "
			+ "case when (select STATUS from schedule_job j where j.id=l.job_id ) ='1' then null else max(END_TIME) end \"endTime\", "
			+ "count(l.job_id) \"executionTimes\", "
			+ "sum(case  when l.STATUS ='1' then 1 else 0 end) \"successTimes\", "
			+ "sum(case  when l.STATUS ='0' then 1 else 0 end) \"failuresTimes\" "
			+ "from  SCHEDULE_LOG l where "
			+ "( ?1 is null or APP_ID in (?1)) and "
			+ "( ?2 is null or JOB_ID in (?2)) and "
			+ "( BEGIN_TIME between to_date(?3,?5) and to_date(?4,?5)) "
			+ "group by l.job_id")
	public List<LogStaticInfo> listStaticInfo(String appid,String jobid,String begintime,String begintime_end,String dateformate);

	
	@Query("FROM Log a where  a.triggerId =(select id from Trigger where name =?1) and beginTime in(select max(beginTime) from Log)")
	public Log findLogBytriggerIdRecentTime(String name);

    @SQL("select * from SCHEDULE_LOG l where l.status='0' and l.send_status='0'")
    public List<Log> findLogBySendStatus();
}

