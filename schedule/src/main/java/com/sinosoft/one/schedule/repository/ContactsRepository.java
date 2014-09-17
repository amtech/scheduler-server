package com.sinosoft.one.schedule.repository;
// Generated 2014-5-12 15:13:57 by One Data Tools 1.0.0

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sinosoft.one.data.jade.annotation.SQL;
import com.sinosoft.one.schedule.model.Contacts;

public interface ContactsRepository extends PagingAndSortingRepository<Contacts, String> {
	 
	@SQL("select * from SCHEDULE_CONTACTS ")
	public List<Contacts>list();
	
	@SQL("select * from SCHEDULE_CONTACTS where APP_ID=?1")
	public List<Contacts> listByAppId(String appId);

    @SQL("update SCHEDULE_CONTACTS SET NAME=?2,MAIL=?3,PHONE_NO=?4,MODIFY_TIME=?5 where id=?1 ")
    public void update(String id,String name, String mail, long phoneNo,Date modifyTime);
}

