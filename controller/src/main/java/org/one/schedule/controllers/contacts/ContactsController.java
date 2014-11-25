package org.one.schedule.controllers.contacts;

import java.util.*;

import com.sinosoft.one.mvc.web.instruction.reply.Replys;
import org.one.schedule.service.MailService;
import org.one.schedule.model.Contacts;
import org.one.schedule.service.AppService;
import org.one.schedule.service.ContactsService;
import org.one.schedule.vo.AppContactsInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Param;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Get;
import com.sinosoft.one.mvc.web.annotation.rest.Post;
import com.sinosoft.one.mvc.web.instruction.reply.Reply;
import org.one.schedule.model.App;
import org.one.schedule.vo.ContactsConfigInfo;

@Path("")
public class ContactsController {
	
	@Autowired
	private ContactsService contactsService;
	
	@Autowired
	private AppService appService;

    @Autowired
    private MailService mailService;
	
	@Get("view")
	public String view(){

		return "contacts";
	}
	
	@Get("list")
	public String contactList(Invocation inv){
		List<Contacts> contacts=contactsService.list();
		inv.addModel("contacts",contacts);
		return "list";
	}
	
	
	@Get("configinfo")
	public String configInfo(Invocation inv){
		List<App>apps=appService.list();
		List<AppContactsInfo> appContactsInfos=new ArrayList<AppContactsInfo>();
		for (App app : apps) {
			AppContactsInfo appContactsInfo=new AppContactsInfo();
			appContactsInfo.setAppid(app.getId());
			appContactsInfo.setAppName(app.getName());
			StringBuilder sb=new StringBuilder();
			for (Contacts contacts : contactsService.listByAppId(app.getId())) {
				sb.append(contacts.getMail());
				sb.append(",");
			}
			int sblength=sb.length();
			if(sblength>0)
				sb.delete(sblength-1, sblength);
			appContactsInfo.setContactsMails(sb.toString());
			appContactsInfos.add(appContactsInfo);
		}
		inv.addModel("appContactsInfos", appContactsInfos);
		return "config_info";
	}
	
	@Get("configlist/{appId}")
	public String configlist(@Param("appId")String appId, Invocation inv){
		List<Contacts>alreadyConfigList=contactsService.listByAppId(appId);
		List<Contacts>contactsList =contactsService.list();
		List<ContactsConfigInfo>contactsConfigInfos=new ArrayList<ContactsConfigInfo>();
		for (Contacts contacts : contactsList) {
			ContactsConfigInfo contactsConfigInfo=new ContactsConfigInfo();
			contactsConfigInfo.setContactsId(contacts.getId());
			contactsConfigInfo.setContactsName(contacts.getName());
			contactsConfigInfo.setContactsMail(contacts.getMail());
			contactsConfigInfo.setFlag("0");
			for (Contacts contacts2 : alreadyConfigList) {
				if(contacts.getId().equals(contacts2.getId())){
					contactsConfigInfo.setFlag("1");
					break;
				}
			}
			contactsConfigInfos.add(contactsConfigInfo);
		}
		inv.addModel("contactsConfigInfos", contactsConfigInfos);
        inv.addModel("appId",appId);
		return "config";
	}
	
	@Post({"add/{name}/{mail}/{phoneNo}","addcontacts/{name}/{mail}"})
	public Reply save(@Param("name")String name,@Param("mail")String mail,@Param("phoneNo")String phoneNo){
		contactsService.save(name, mail, phoneNo);
		return null;
	}

    @Get("toModify")
    public String toModify(@Param("contactsId")String contactsId,Invocation inv){
        Contacts contacts = contactsService.findOne(contactsId);
        inv.addModel("contacts",contacts);
        return "update";
    }

    @Post("modify")
    public Reply modify(Contacts contacts,Invocation inv){
        contacts.setModifyTime(new Date());
        contactsService.update(contacts);
        return null;
    }

    @Get("delete")
    public String delete(@Param("contactsId")String contactsId,Invocation inv){
        contactsService.delete(contactsId);
        List<Contacts> contacts=contactsService.list();
        inv.addModel("contacts",contacts);
        return "list";
    }

//    @Get("sendMail")
//    public Reply sendMail(@Param("mail")String mail){
//        mailService.sendMail(mail);
//        Map<String,String> result = new HashMap<String, String>();
//        result.put("status","200");
//        return Replys.with(result);
//    }

    @Post("config")
    public Reply config(@Param("appId")String appId,@Param("contactsIds") String contactsIds){
        App app = appService.findById(appId);

        if(!contactsIds.equals("")&&contactsIds!=null){
            app.getContactses().clear();
            String[] array = contactsIds.split(",");
            for(int i=0;i<array.length;i++){
                Contacts contacts = contactsService.findOne(array[i]);
                app.getContactses().add(contacts);
            }
        }else{
            app.getContactses().clear();
        }
        appService.update(app);
        return Replys.simple().success();
    }
}
