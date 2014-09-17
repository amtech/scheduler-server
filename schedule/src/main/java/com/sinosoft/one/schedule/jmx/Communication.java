package com.sinosoft.one.schedule.jmx;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;
import javax.management.remote.JMXConnector;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.one.schedule.model.App;
import com.sinosoft.one.schedule.model.Job;
import com.sinosoft.one.schedule.model.Trigger;

@Service
public class Communication {

	@Autowired
	private Connector jmsConnecto;


    public List<Job> getAllJobDetails(App app) {
		List<Job> jobs=new ArrayList<Job>();
		try {
			JMXConnector jmxc = jmsConnecto.creatJMXServiceURL(app);
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			ObjectName  mbeanName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
			TabularDataSupport tabularDataSupport =(TabularDataSupport)mbsc.getAttribute(mbeanName, "AllJobDetails");
			if(tabularDataSupport!=null){
				for (Object obj : tabularDataSupport.values()) {
					if(!(obj instanceof CompositeDataSupport)){
						continue;
					}
					CompositeDataSupport compositeDataSupport =(CompositeDataSupport)obj;
					Job job=new Job();
					job.setClass_((String) Connector.convertToType(compositeDataSupport, "jobClass"));
					job.setName((String) Connector.convertToType(compositeDataSupport, "name"));
					if(app.getStatus().equals("1")){
						job.setStatus("1");
					}else {
						job.setStatus("0");
					}
					job.setRecordStatus("1");
                    job.setCreateTime(Calendar.getInstance().getTime());
					jobs.add(job);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobs;
	}
	
	
	
	public List<Trigger> getAllTrigger(App app,List<Job>jobs){
		List<Trigger> Triggers=new ArrayList<Trigger>();
		try {
			JMXConnector jmxc = jmsConnecto.creatJMXServiceURL(app);
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			ObjectName  mbeanName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
			List<CompositeDataSupport> compositeDataSupports =(List<CompositeDataSupport>)mbsc.getAttribute(mbeanName, "AllTriggers");
			if(compositeDataSupports!=null){
				for (CompositeDataSupport compositeDataSupport : compositeDataSupports) {
					for (Job job :jobs) {
						if(job.getName().equals((String) Connector.convertToType(compositeDataSupport, "jobName"))){
							Trigger trigger=new Trigger();
							trigger.setAppId(app.getId());
							trigger.setJob(job);
							trigger.setName((String) Connector.convertToType(compositeDataSupport, "name"));
							trigger.setRecordStatus("1");
                            trigger.setCreateTime(Calendar.getInstance().getTime());
							Triggers.add(trigger);
						}
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Triggers;
	}

    /**
     * 增加和修改计划
     * @param trigger
     */
    public void save(App app,Trigger trigger) throws ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        JMXConnector jmxc = null;
        MBeanServerConnection mbsc = null;
        ObjectName  objectName = null;
        try {
            jmxc = jmsConnecto.creatJMXServiceURL(app);
            mbsc = jmxc.getMBeanServerConnection();
            objectName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        Map<String, Object> triggerMap = new HashMap<String, Object>();
        triggerMap.put("triggerClass","org.quartz.impl.triggers.CronTriggerImpl");
        triggerMap.put("name",trigger.getName());
        triggerMap.put("jobName",trigger.getJob().getName());
        CronExpression cronExpression = null;
        try {
            cronExpression = new CronExpression(trigger.getCronExpression());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        triggerMap.put("CronExpression",cronExpression);
        mbsc.invoke(objectName,"scheduleJob",new Object[]{trigger.getJob().getName(),"DEFAULT",triggerMap},new String[]{"java.lang.String","java.lang.String","java.util.Map"});
    }

    /**
     * 删除计划
     * @param trigger
     */
    public void delete(App app,Trigger trigger) throws ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        JMXConnector jmxc = null;
        MBeanServerConnection mbsc = null;
        ObjectName  objectName = null;
        try {
            jmxc = jmsConnecto.creatJMXServiceURL(app);
            mbsc = jmxc.getMBeanServerConnection();
            objectName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        mbsc.invoke(objectName,"unscheduleJob",new Object[]{trigger.getName(),"DEFAULT"} ,new String[]{"java.lang.String","java.lang.String"});
    }

    /**
     * 计划停止
     * @param trigger
     * @throws ReflectionException
     * @throws MBeanException
     * @throws InstanceNotFoundException
     * @throws IOException
     */
    public void pauseTrigger(App app,Trigger trigger) throws ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        JMXConnector jmxc = null;
        MBeanServerConnection mbsc = null;
        ObjectName  objectName = null;
        try {
            jmxc = jmsConnecto.creatJMXServiceURL(app);
            mbsc = jmxc.getMBeanServerConnection();
            objectName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        mbsc.invoke(objectName,"pauseTrigger",new Object[]{trigger.getName(),"DEFAULT"},new String[]{"java.lang.String","java.lang.String"});
    }

    /**
     * 计划开始
     * @param trigger
     */
    public void resumeTrigger(App app,Trigger trigger) throws ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        JMXConnector jmxc = null;
        MBeanServerConnection mbsc = null;
        ObjectName  objectName = null;
        try {
            jmxc = jmsConnecto.creatJMXServiceURL(app);
            mbsc = jmxc.getMBeanServerConnection();
            objectName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        mbsc.invoke(objectName,"resumeTrigger",new Object[]{trigger.getName(),"DEFAULT"},new String[]{"java.lang.String","java.lang.String"});
    }

    /**
     * 立即执行
     * @param job
     * @return
     */
    public void triggerJob(App app,Job job) throws ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        JMXConnector jmxc = null;
        MBeanServerConnection mbsc = null;
        ObjectName  objectName = null;
        try {
            jmxc = jmsConnecto.creatJMXServiceURL(app);
            mbsc = jmxc.getMBeanServerConnection();
            objectName =  new ObjectName("quartz:type=QuartzScheduler,name=QuartzScheduler,instance=core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        mbsc.invoke(objectName,"triggerJob",new Object[]{job.getName(),"DEFAULT",new HashMap<String, String>()},new String[]{"java.lang.String","java.lang.String","java.util.Map"});
    }
}
