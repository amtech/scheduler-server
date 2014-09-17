package com.sinosoft.one.schedule.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.one.schedule.model.Contacts;
import com.sinosoft.one.schedule.repository.ContactsRepository;

@Service
public class ContactsService {

	@Autowired
	private ContactsRepository contactsRepository;
	
	public List<Contacts>list (){
		return contactsRepository.list();
	}
	
	public List<Contacts>listByAppId(String appId){
		return contactsRepository.listByAppId(appId);
	}
	
	public void save (String name,String mail,String phoneNo){
		Contacts contacts=new Contacts();
		contacts.setName(name);
		contacts.setMail(mail);
		contacts.setRecordTime(new Date());
		contacts.setRecordStatus("1");
		if(phoneNo!=null){
			contacts.setPhoneNo(Long.parseLong(phoneNo));
		}
		contactsRepository.save(contacts);
	}

    public Contacts findOne(String id){
        return contactsRepository.findOne(id);
    }

    public void update(Contacts contacts){
        String id = contacts.getId();
        String name = contacts.getName();
        String mail = contacts.getMail();
        long phoneNo = contacts.getPhoneNo();
        Date modifyTime = contacts.getModifyTime();
        contactsRepository.update(id,name,mail,phoneNo,modifyTime);
    }

    public void delete(String id){
        contactsRepository.delete(id);
    }
}
