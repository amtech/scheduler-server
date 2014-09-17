package com.sinosoft.one.schedule.jmx;

import com.sinosoft.one.schedule.model.App;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Date;

@Service
public class Connector {

	public JMXConnector creatJMXServiceURL(App app) throws IOException {

		String namingHost = app.getIp();
		String namingPort = app.getPort().toString();
		String jndiPath = app.getName();
		JMXServiceURL jmxUrl = new JMXServiceURL("service:jmx:rmi://"+namingHost+":"+namingPort+"/jndi/rmi://"
				+ namingHost + ":" + namingPort +"/"+ jndiPath);
		return JMXConnectorFactory.connect(jmxUrl);
	}

	public static Object convertToType(
			CompositeDataSupport compositeDataSupport, String key) {
		if (compositeDataSupport.getCompositeType().getType(key).getClassName()
				.equals("java.lang.String")) {
			return StringUtils.trimToEmpty((String) compositeDataSupport
					.get(key));
		} else if (compositeDataSupport.getCompositeType().getType(key)
				.getClassName().equals("java.lang.Boolean")) {
			return compositeDataSupport.get(key);
		} else if (compositeDataSupport.getCompositeType().getType(key)
				.getClassName().equals("java.util.Date")) {
			return (Date) compositeDataSupport.get(key);
		} else if (compositeDataSupport.getCompositeType().getType(key)
				.getClassName().equals("java.lang.Integer")) {
			return (Integer) compositeDataSupport.get(key);
		} else if (compositeDataSupport.getCompositeType().getType(key)
				.getClassName().equals("java.lang.Long")) {
			return (Long) compositeDataSupport.get(key);
		}
		return new Object();
	}
}
